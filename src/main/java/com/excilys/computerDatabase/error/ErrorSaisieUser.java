package com.excilys.computerDatabase.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ErrorSaisieUser extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ErrorSaisieUser.class);
	
	public void formatEntry() {
		logger.error("entrée invalide");
	}
}
