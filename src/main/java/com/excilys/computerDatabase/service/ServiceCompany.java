package com.excilys.computerDatabase.service;

import java.util.List;

import com.excilys.computerDatabase.dao.DAOCompany;
import com.excilys.computerDatabase.data.Company;

public class ServiceCompany extends Service{
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
