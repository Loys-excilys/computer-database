package com.excilys.computerDatabase.service;

import java.util.List;

import com.excilys.computerDatabase.dao.DAOCompany;
import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.error.ErrorDAOCompany;

public class ServiceCompany extends Service{
	private DAOCompany database;
	
	public ServiceCompany() {
		this.database = new DAOCompany();
	}
	
	public Company getCompany(String nameCompany) throws ErrorDAOCompany {
		return this.database.getCompany(nameCompany);
	}

	public List<Company> getListCompany() throws ErrorDAOCompany {
		return this.database.getListCompany();
	}
	
	public List<Company> getListCompany(int page) throws ErrorDAOCompany{
		return this.database.getListCompany(page);
	}


}
