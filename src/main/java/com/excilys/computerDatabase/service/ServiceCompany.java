package com.excilys.computerDatabase.service;

import java.sql.SQLException;
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
	
	public Company getCompany(String nameCompany){
		return this.database.getCompany(nameCompany).get();
	}

	public List<Company> getListCompany(){
		return this.database.getListCompany();
	}
	
	public List<Company> getListCompany(int page){
		return this.database.getListCompany(page);
	}
	
	public void deleteCompanyById(int id){
		this.database.deleteCompanyById(id);
	}
}
