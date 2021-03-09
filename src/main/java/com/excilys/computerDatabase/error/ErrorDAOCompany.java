package com.excilys.computerDatabase.error;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.dao.DAOCompany;

@SuppressWarnings("serial")
public class ErrorDAOCompany extends SQLException{
	
	private static final Logger logger = LoggerFactory.getLogger(DAOCompany.class);
	
	public void idInvalid(SQLException errorSQL) {
		logger.error("Id renseigner incorrect", errorSQL);
	}
	
	public void connectionLost(SQLException exceptionSQL) {
		logger.error("Connection à la base de donnée perdu : ", exceptionSQL);
	}
}
