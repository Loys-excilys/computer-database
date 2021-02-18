package com.excilys.computerDatabase.error;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class ErrorDAOComputer extends SQLException{
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorDAOComputer.class);

	public void idInvalid() {
		logger.error("Id renseigner incorrect");
	}
	
	public void connectionLost() {
		logger.error("Connection à la base de donnée perdu");
	}
	
	public void insertError() {
		logger.error("insert computer impossible, vérifier les valeurs données et la connection à la base de donnée");
	}
	
	public void updateError() {
		logger.error("Update computer impossible, vérifier les valeurs données et la connection à la base de donnée");
	}
	
	public void deleteError() {
		logger.error("Delete computer impossible, vérifier les valeurs données et la connection à la base de donnée");
	}
}
