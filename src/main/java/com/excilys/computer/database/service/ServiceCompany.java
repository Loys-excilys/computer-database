package com.excilys.computer.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.computer.database.dao.DAOCompany;
import com.excilys.computer.database.data.Company;

@Component
public class ServiceCompany{
	
	@Autowired
	private DAOCompany database;
	
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
