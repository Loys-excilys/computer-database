package model;

import java.sql.SQLException;
import java.util.List;

import contract.IModel;

public class Model implements IModel{
	
	private DAODatabase database;
	
	public Model() {
		try {
			this.database = new DAODatabase(DBConnection.getInstance().getConnection());
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public List<DataComputer> listComputer() {
		return this.database.getComputer();		
	}
}
