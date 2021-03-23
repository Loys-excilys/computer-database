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
@ComponentScan({ "com.excilys.computer.database.dao", "com.excilys.computer.database.service",
	"com.excilys.computer.database.controller",
	"com.excilys.computer.database.webapp.config" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    DataSource dataSource;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        .jdbcAuthentication()
        .dataSource(dataSource);
//        .inMemoryAuthentication()
//        .withUser("user").password(passwordEncoder.encode("lol")).roles("USER")
//        .and()
//        .withUser("admin").password(passwordEncoder.encode("lol")).roles("USER", "ADMIN");
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/login")
            .permitAll()
        .antMatchers("/**")
            .hasAnyRole("ADMIN", "USER")
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