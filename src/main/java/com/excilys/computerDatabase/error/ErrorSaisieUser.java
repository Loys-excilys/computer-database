package com.excilys.computerDatabase.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ErrorSaisieUser extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final transient Logger logger;
	
	public ErrorSaisieUser(Class<?> classe) {
		this.logger = LoggerFactory.getLogger(classe.getName());
	}
	
	public void formatEntry() {
		logger.error("entrée invalide");
	}
}
