package com.excilys.computer.database.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan({ "com.excilys.computer.database.dao", "com.excilys.computer.database.service"})
@EnableAspectJAutoProxy
public class ServiceConfigContext {
}
