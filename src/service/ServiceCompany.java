package service;

import java.util.List;

import DAO.DAOCompany;
import data.Company;

public class ServiceCompany {
	private DAOCompany database;
	
	public ServiceCompany() {
		this.database = new DAOCompany();
	}
	
	public Company getCompany(String nameCompany) {
		return this.database.getCompany(nameCompany);
	}

	public List<Company> getListCompany(int page){
		return this.database.getListCompany(page);
	}
}
