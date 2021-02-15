package model;

import java.sql.Connection;
import java.sql.SQLException;

class DAOEntity {
	
	private final Connection connection;
	
	public DAOEntity(final Connection connection)throws SQLException{
		this.connection = connection;
	}
	
	protected Connection getConnection() {
		return this.connection;
	}

}
