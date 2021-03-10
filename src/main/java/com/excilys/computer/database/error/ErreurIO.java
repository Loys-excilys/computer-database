package com.excilys.computer.database.error;

import java.io.IOException;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ErreurIO extends IOException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final transient Logger logger;

	public ErreurIO(Class<?> classe) {
		this.logger = LoggerFactory.getLogger(classe.getName());
	}
	
	public void erreurChargementFileProperties() {
		logger.error("Erreur lors du chargement des fichiers properties");
	}
	
	public void redirectionFail(ServletException errorServlet) {
		logger.error("Redirection impossible : ", errorServlet);
	}

	public void redirectionFail(IOException errorIO) {
		logger.error("Redirection impossible : ", errorIO);
		
	}
}
