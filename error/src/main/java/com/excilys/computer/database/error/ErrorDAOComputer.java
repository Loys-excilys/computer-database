package com.excilys.computer.database.error;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("serial")
public class ErrorDAOComputer extends SQLException {

	private final transient Logger logger;

	public ErrorDAOComputer(Class<?> classe) {
		this.logger = LoggerFactory.getLogger(classe.getName());
	}

	public void idInvalid(SQLException exception) {
		logger.error("Id renseigner incorrect : ", exception);
	}

	public void connectionLost(SQLException exception) {
		logger.error("Connection à la base de donnée perdu : ", exception);
	}

	public void insertError(SQLException exception) {
		logger.error("insert computer impossible, vérifier les valeurs données et la connection à la base de donnée : ",
				exception);
	}

	public void updateError(SQLException exception) {
		logger.error("Update computer impossible, vérifier les valeurs données et la connection à la base de donnée : ",
				exception);
	}

	public void deleteError(SQLException exception) {
		logger.error("Delete computer impossible, vérifier les valeurs données et la connection à la base de donnée : ",
				exception);
	}
}
