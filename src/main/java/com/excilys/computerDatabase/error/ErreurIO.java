package com.excilys.computerDatabase.error;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class ErreurIO extends IOException{
	
	private Logger logger;

	public ErreurIO(Class<?> classe) {
		this.logger = LoggerFactory.getLogger(classe.getName());
	}
	
	public void erreurChargementFileProperties() {
		logger.error("Erreur lors du chargement des fichiers properties");
	}
	
	public void redirectionFail() {
		logger.error("Redirection impossible");
	}
}
