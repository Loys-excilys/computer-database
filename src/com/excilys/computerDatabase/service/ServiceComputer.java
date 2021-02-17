package com.excilys.computerDatabase.service;

import java.util.List;

import com.excilys.computerDatabase.dao.DAOComputer;
import com.excilys.computerDatabase.data.Computer;

public class ServiceComputer extends Service{
	
	private DAOComputer database;
	
	public ServiceComputer() {
		this.database = new DAOComputer();
	}
	
	public Computer getComputer(int id) {
		return this.database.getComputer(id);
	}
	
	public List<Computer> getListComputer(int page) {
		return this.database.getListComputer(page);	
	}
		
	public void addComputer(Computer computer) {
		this.database.insertComputer(computer);
	}
	
	public void updateComputer(Computer computer) {
		this.database.updateComputer(computer);
	}
	
	public void deleteComputer(int id) {
		if(id != -1) {
			this.database.deleteComputer(id);
		}
	}
}
