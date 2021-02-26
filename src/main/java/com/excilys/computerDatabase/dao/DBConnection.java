package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.excilys.computerDatabase.error.ErrorDriver;

public final class DBConnection {
	private static DBConnection INSTANCE = null;
	
	private Connection connection;
	
	private DBConnection() {
		this.open();
	}
	
	public static synchronized DBConnection getInstance() {
		if(DBConnection.INSTANCE == null) {
			DBConnection.INSTANCE = new DBConnection();
		}
	return DBConnection.INSTANCE;
	}
	
	private Boolean open(){
		final DBProperties dbProperties = new DBProperties();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(dbProperties.getUrl(), dbProperties.getLogin(), dbProperties.getPassword());
		}catch(ClassNotFoundException errorClass){
			
			new ErrorDriver().DriverNotFound();
		} catch (SQLException errorSQL) {
			new ErrorDriver().DriverNotFound();
		}
		return true;
	}
	
	public Connection getConnection() throws SQLException{
		if(this.connection.isClosed()) {
			this.open();
		}
		return this.connection;
	}
}
