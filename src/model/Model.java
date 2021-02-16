package model;

import java.sql.SQLException;
import java.util.List;

import contract.IModel;
import data.Company;
import data.Computer;

public class Model implements IModel{
	
	private DAODatabase database;
	private Page page = new Page();
	
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
		if(this.page.getType() != null ? this.page.getType().compareTo("computer") == 0 : false) {
			this.page.setNum(this.page.getNum() + 1);
		}else {
			this.page.setType("computer");
			this.page.setNum(0);
		}
		return this.database.getListComputer(this.page.getNum());	
	}
	
	public List<Company> getListCompany(){
		if(this.page.getType() != null ? this.page.getType().compareTo("company") == 0 : false) {
			this.page.setNum(this.page.getNum() + 1);
		}else {
			this.page.setType("company");
			this.page.setNum(0);
		}
		return this.database.getListCompany(this.page.getNum());
	}
	
	public void addComputer(Computer computer) {
		this.database.insertComputer(computer);
	}
	
	public void updateComputer(Computer computer) {
		this.database.updateComputer(computer);
	}
	
	public void deleteComputer(int id) {
		this.database.deleteComputer(id);
	}
}
