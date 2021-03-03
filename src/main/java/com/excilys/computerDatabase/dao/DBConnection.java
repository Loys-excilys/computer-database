package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class DBConnection {
	private static DBConnection INSTANCE = null;
	
	private HikariDataSource connection;
	private HikariConfig config = new HikariConfig();
	
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
		final DBProperties dbProperties = DBProperties.getInstance();
		config.setDriverClassName(dbProperties.getDriver());
		config.setJdbcUrl(dbProperties.getUrl());
		config.setUsername(dbProperties.getLogin());
		config.setPassword(dbProperties.getPassword());
		config.addDataSourceProperty( "cachePrepStmts" , "true" );
		config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
		config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
		connection = new HikariDataSource( config );
		return true;
	}
	
	public Connection getConnection() throws SQLException{
		if(this.connection.isClosed()) {
			this.open();
		}
		return this.connection.getConnection();
	}
}
