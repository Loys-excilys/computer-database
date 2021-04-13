package com.excilys.computer.database.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan({ "com.excilys.computer.database.dao", "com.excilys.computer.database.config" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder)
		.usersByUsernameQuery("SELECT username, password, enabled "
				+ "FROM users "
				+ "WHERE username = ?")
		.authoritiesByUsernameQuery("SELECT users.username, authorities.authority "
				+ "FROM users "
				+ "LEFT JOIN authorities ON users.authority_id = authorities.id "
				+ "WHERE users.username = ?");
	}
	
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() 
      throws Exception {
        return super.authenticationManagerBean();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**")
        .authorizeRequests()
        	.mvcMatchers("/oauth/authorize**", "/login**", "/error**")
        		.permitAll()
        .and()
        	.authorizeRequests()
        	.anyRequest()
        		.authenticated()
        .and()
        	.formLogin()
        	.permitAll();
	}
}