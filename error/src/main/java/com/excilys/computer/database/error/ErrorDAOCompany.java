package com.excilys.computer.database.error;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class ErrorDAOCompany extends SQLException {

	private final transient Logger logger;

	public ErrorDAOCompany(Class<?> classe) {
		this.logger = LoggerFactory.getLogger(classe.getName());
	}


	public void idInvalid(SQLException errorSQL) {
		logger.error("Id renseigner incorrect", errorSQL);
	}

	public void connectionLost(SQLException exceptionSQL) {
		logger.error("Connection à la base de donnée perdu : ", exceptionSQL);
	}
}
