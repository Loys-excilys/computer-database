package com.excilys.computer.database.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public final class DBConnection {

	private HikariDataSource connection;

	private Boolean open() {
		HikariConfig config = new HikariConfig("/db.properties");
		connection = new HikariDataSource(config);
		return true;
	}

	public Connection getConnection() throws SQLException {
		if (this.connection == null || this.connection.isClosed()) {
			this.open();
		}
		return this.connection.getConnection();
	}
}
