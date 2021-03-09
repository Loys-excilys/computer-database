package com.excilys.computerDatabase.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@ComponentScan({ "com.excilys.computerDatabase.dao", "com.excilys.computerDatabase.service",
		"com.excilys.computerDatabase.controller", "com.excilys.computerDatabase.view"})

public class ConfigContext 
extends AbstractContextLoaderInitializer {

  @Override
  protected WebApplicationContext createRootApplicationContext() {
      AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
      rootContext.register(ConfigContext.class);
      return rootContext;
  }
}