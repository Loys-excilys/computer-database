package com.excilys.computer.database.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.excilys.computer.database.service", "com.excilys.computer.database.console.controller",
		"com.excilys.computer.database.view", "com.excilys.computer.database.config",
		"com.excilys.computer.database.stream", "com.excilys.computer.database.error" })
public class ConsoleConfigContext {
}
