package com.excilys.computer.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.computer.database.dao.DAOCompany;
import com.excilys.computer.database.data.Company;

@Service
public class ServiceCompany {

	private DAOCompany database;

	
	public ServiceCompany(DAOCompany database) {
		this.database = database;
	}
	
	public Company getCompany(String nameCompany) {
		return this.database.getCompany(nameCompany);
	}

	public List<Company> getListCompany() {
		return this.database.getListCompany();
	}

	public List<Company> getListCompany(int page) {
		return this.database.getListCompany(page);
	}

	public void deleteCompanyById(int id) {
		this.database.deleteCompanyById(id);
	}
}
