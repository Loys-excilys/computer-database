package com.excilys.computer.database.error;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.excilys.computer.database.service", "com.excilys.computer.database.log"})
public class ErrorConfigContext {
}
