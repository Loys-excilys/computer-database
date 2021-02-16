package DAO;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

@SuppressWarnings("serial")
class DBProperties extends Properties{

	private final static String PROPERTIES_FILE_NAME = "db.properties";
	
	private String url = "";
	private String login = "";
	private String password = "";
	
	public DBProperties() {
		InputStream inputStream;
		
		inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
		
		if(inputStream != null) {
			try {
				this.load(inputStream);
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			this.setUrl(this.getProperty("url"));
			this.setLogin(this.getProperty("login"));
			this.setPassword(this.getProperty("password"));
		}
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
