package com.excilys.computer.database.webapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan({ "com.excilys.computer.database.dao", "com.excilys.computer.database.webapp.config", "com.excilys.computer.database.persistence.config"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    DataSource dataSource;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
    
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(passwordEncoder);
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .mvcMatchers("/login")
            .permitAll()
        .anyRequest()
        	.authenticated()
        .and()
            .httpBasic()
            .realmName("TEST REALM")
            .authenticationEntryPoint(authenticationEntryPoint)
        .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/Computer")
            .failureUrl("/login?error=true")
            .permitAll()
        .and()
            .logout()
            .logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true)
            .permitAll()
        .and()
            .csrf()
            .disable();
    }
}