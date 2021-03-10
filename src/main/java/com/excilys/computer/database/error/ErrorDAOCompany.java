package com.excilys.computer.database.error;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer.database.dao.DAOCompany;

@SuppressWarnings("serial")
public class ErrorDAOCompany extends SQLException {

	private static final Logger LOGGER = LoggerFactory.getLogger(DAOCompany.class);

	public void idInvalid(SQLException errorSQL) {
		LOGGER.error("Id renseigner incorrect", errorSQL);
	}

	public void connectionLost(SQLException exceptionSQL) {
		LOGGER.error("Connection à la base de donnée perdu : ", exceptionSQL);
	}
}
