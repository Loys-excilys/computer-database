package com.excilys.computer.database.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@ComponentScan({ "com.excilys.computer.database.dao", "com.excilys.computer.database.service",
		"com.excilys.computer.database.controller", "com.excilys.computer.database.view"})

public class ConfigContext 
extends AbstractContextLoaderInitializer {

  @Override
  protected WebApplicationContext createRootApplicationContext() {
      AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
      rootContext.register(ConfigContext.class);
      return rootContext;
  }
}