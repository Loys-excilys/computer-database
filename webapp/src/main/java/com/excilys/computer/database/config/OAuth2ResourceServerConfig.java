package com.excilys.computer.database.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter   
{	
	
	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	            	.mvcMatchers("/", "/csrf", "/v2/api-docs", "/swagger-resources/configuration/ui",
						"/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security",
						"/configuration/security", "/swagger-ui.html", "/webjars/**", "/login/**", "/oauth/**", "/error**")
	            		.permitAll()
	            	.anyRequest()
	            		.fullyAuthenticated()	
	            .and()
					.cors()
				.and()
					.csrf()
					.disable();
	    }
	    
		@Bean
		CorsConfigurationSource corsConfigurationSource() {
		    CorsConfiguration configuration = new CorsConfiguration();
		    ArrayList<String> list = new ArrayList<>();
		    list.add("*");
		    configuration.setAllowedOrigins(list);
		    configuration.addAllowedHeader("*");
		    configuration.addAllowedMethod("*");
		    configuration.setAllowCredentials(true);
		    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		    source.registerCorsConfiguration("/**", configuration);
		    return source;
		}
}
