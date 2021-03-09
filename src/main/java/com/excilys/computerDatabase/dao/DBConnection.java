package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
@Scope("singleton")
public final class DBConnection {
	
	private HikariDataSource connection;
	private HikariConfig config = new HikariConfig();
	
	private Boolean open(){
		DBProperties dbProperties = DBProperties.getInstance();
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
		if(this.connection == null || this.connection.isClosed()) {
			this.open();
		}
		return this.connection.getConnection();
	}
}
