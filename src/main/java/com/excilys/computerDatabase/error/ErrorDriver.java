package com.excilys.computerDatabase.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.dao.DBConnection;

public class ErrorDriver extends ClassNotFoundException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LoggerFactory.getLogger(DBConnection.class);;

	public ErrorDriver() {}
	
	public void DriverNotFound() {
		logger.error("Driver Not Found");
	}
}
