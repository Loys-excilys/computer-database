package com.excilys.computerDatabase.dao;

import java.util.Properties;

import com.excilys.computerDatabase.error.ErreurIO;

import java.io.InputStream;
import java.io.IOException;

@SuppressWarnings("serial")
class DBProperties extends Properties{

	private final static String PROPERTIES_FILE_NAME = "db.properties";

	private static DBProperties INSTANCE;
	
	private String url = "";
	private String login = "";
	private String password = "";
	
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
	
}
