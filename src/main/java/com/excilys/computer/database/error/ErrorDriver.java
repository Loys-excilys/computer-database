package com.excilys.computer.database.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer.database.dao.DBConnection;

public class ErrorDriver extends ClassNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final transient Logger logger = LoggerFactory.getLogger(DBConnection.class);

	public void driverNotFound() {
		logger.error("Driver Not Found");
	}
}
