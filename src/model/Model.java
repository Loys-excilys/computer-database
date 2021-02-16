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
	
	public Computer getComputer(int id) {
		return this.database.getComputer(id);
	}
	
	public Company getCompany(String nameCompany) {
		return this.database.getCompany(nameCompany);
	}

	public List<Computer> getListComputer() {
		return this.database.getListComputer();		
	}
	
	public List<Company> getListCompany(){
		return this.database.getListCompany();
	}
	
	public void addComputer(Computer computer) {
		this.database.insertComputer(computer);
	}
	
	public void updateComputer(Computer computer) {
		this.database.updateComputer(computer);
	}
}
