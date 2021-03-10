package com.excilys.computer.database.dao;

import java.util.Properties;

import com.excilys.computer.database.error.ErreurIO;

import java.io.InputStream;
import java.io.IOException;

class DBProperties extends Properties{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String PROPERTIES_FILE_NAME = "db.properties";

	private static DBProperties INSTANCE;
	
	private String url = "";
	private String login = "";
	private String password = "";
	private String driver = "";
	
	private DBProperties() {
		InputStream inputStream;
		
		inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
		
		if(inputStream != null) {
			try {
				this.load(inputStream);
			}catch(IOException erreurIO) {
				ErreurIO erreurFile = new ErreurIO(this.getClass());
				erreurFile.erreurChargementFileProperties();
			}
			
			this.setUrl(this.getProperty("url"));
			this.setLogin(this.getProperty("login"));
			this.setPassword(this.getProperty("password"));
			this.setDriver(this.getProperty("driver"));
		}
	}
	
	public static synchronized DBProperties getInstance() {
		if(DBProperties.INSTANCE == null) {
			DBProperties.INSTANCE = new DBProperties();
		}
	return DBProperties.INSTANCE;
	}
	
	private void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return this.url;
	}
	
	private void setLogin(String login) {
		this.login = login;
	}
	public String getLogin() {
		return this.login;
	}
	
	private void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
	
}
