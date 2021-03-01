package com.excilys.computerDatabase.service;

import java.util.List;

import com.excilys.computerDatabase.dao.DAOCompany;
import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.error.ErrorDAOCompany;

public class ServiceCompany{
	private static ServiceCompany INSTANCE;
	private DAOCompany database;
	
	private ServiceCompany() {
		this.database = DAOCompany.getInstance();
	}
	
	public static synchronized ServiceCompany getInstance() {
		if(ServiceCompany.INSTANCE == null) {
			ServiceCompany.INSTANCE = new ServiceCompany();
		}
	return ServiceCompany.INSTANCE;
	}
	
	public Company getCompany(String nameCompany) throws ErrorDAOCompany {
		return this.database.getCompany(nameCompany).get();
	}

	public List<Company> getListCompany() throws ErrorDAOCompany {
		return this.database.getListCompany();
	}
	
	public List<Company> getListCompany(int page) throws ErrorDAOCompany{
		return this.database.getListCompany(page);
	}


}
