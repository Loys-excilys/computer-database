package com.excilys.computer.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.computer.database.dao.DAOCompany;
import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.error.ErrorSaisieUser;

@Service
public class ServiceCompany {

	private DAOCompany database;

	
	public ServiceCompany(DAOCompany database) {
		this.database = database;
	}
	
	public Company getCompany(String nameCompany) throws ErrorSaisieUser {
		return this.database.getCompany(nameCompany);
	}

	public List<Company> getListCompany() {
		return this.database.getListCompany();
	}

	public List<Company> getListCompany(int page) {
		return this.database.getListCompany(page);
	}
	
	public void addCompany(Company company) {
		this.database.insertCompany(company);
	}
	
	public void updateCompany(Company company) {
		this.database.updateCompany(company);
	}

	public void deleteCompanyById(int id) throws ErrorSaisieUser {
		this.database.deleteCompanyById(id);
	}
}
