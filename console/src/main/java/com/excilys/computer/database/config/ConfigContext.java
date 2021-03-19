package com.excilys.computer.database.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.excilys.computer.database.service",
		"com.excilys.computer.database.controller", "com.excilys.computer.database.view",
		"com.excilys.computer.database.config" })
public class ConfigContext {
}
