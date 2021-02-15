package model;

import java.sql.SQLException;
import java.util.List;

import contract.IModel;
import data.Company;
import data.Computer;

public class Model implements IModel{
	
	private DAODatabase database;
	
	public Model() {
		try {
			this.database = new DAODatabase(DBConnection.getInstance().getConnection());
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Computer> getListComputer() {
		return this.database.getComputer();		
	}
	
	public List<Company> getListCompany(){
		return this.database.getCompany();
	}
	
	public Computer getDetailComputer(int id) {
		return this.database.getDetailComputer(id);
	}
	
	public void addComputer(Computer computer) {
		this.database.insertComputer(computer);
	}
}
