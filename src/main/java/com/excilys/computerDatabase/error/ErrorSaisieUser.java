package com.excilys.computerDatabase.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ErrorSaisieUser {

	private static final Logger logger = LoggerFactory.getLogger(ErrorSaisieUser.class);
	
	public void formatEntry() {
		logger.error("entrée invalide");
	}
}
