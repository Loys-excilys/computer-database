package com.excilys.computerDatabase.error;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class ErrorDAOCompany extends SQLException{
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorDAOComputer.class);
	
	public void idInvalid() {
		logger.error("Id renseigner incorrect");
	}
	
	public void connectionLost() {
		logger.error("Connection à la base de donnée perdu");
	}
}
