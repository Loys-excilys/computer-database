package com.excilys.computer.database.error;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer.database.dao.DAOComputer;

@SuppressWarnings("serial")
public class ErrorDAOComputer extends SQLException {

	private static final Logger LOGGER = LoggerFactory.getLogger(DAOComputer.class);

	public void idInvalid(SQLException exception) {
		LOGGER.error("Id renseigner incorrect : ", exception);
	}

	public void connectionLost(SQLException exception) {
		LOGGER.error("Connection à la base de donnée perdu : ", exception);
	}

	public void insertError(SQLException exception) {
		LOGGER.error("insert computer impossible, vérifier les valeurs données et la connection à la base de donnée : ",
				exception);
	}

	public void updateError(SQLException exception) {
		LOGGER.error("Update computer impossible, vérifier les valeurs données et la connection à la base de donnée : ",
				exception);
	}

	public void deleteError(SQLException exception) {
		LOGGER.error("Delete computer impossible, vérifier les valeurs données et la connection à la base de donnée : ",
				exception);
	}
}
