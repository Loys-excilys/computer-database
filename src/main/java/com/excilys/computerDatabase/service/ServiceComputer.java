package com.excilys.computerDatabase.service;

import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.dao.DAOComputer;
import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.error.ErrorDAOComputer;

public class ServiceComputer extends Service{
	
	private DAOComputer database;
	
	public ServiceComputer() {
		this.database = new DAOComputer();
	}
	
	public Optional<Computer> getComputer(int id) throws ErrorDAOComputer {
		return this.database.getComputer(id);
	}
	
	public List<Computer> getListComputer(int page) throws ErrorDAOComputer {
		return this.database.getListComputer(page);	
	}
		
	public void addComputer(Computer computer) throws ErrorDAOComputer {
		this.database.insertComputer(computer);
	}
	
	public void updateComputer(Computer computer) throws ErrorDAOComputer {
		this.database.updateComputer(computer);
	}
	
	public void deleteComputer(int id) throws ErrorDAOComputer {
		if(id != -1) {
			this.database.deleteComputer(id);
		}
	}
}
