package com.excilys.computer.database.config;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint{

	 @Override
	 public void afterPropertiesSet() {
		 setRealmName("TEST REALM");
	 }
}
