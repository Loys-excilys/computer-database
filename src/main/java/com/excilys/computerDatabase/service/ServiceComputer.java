package com.excilys.computerDatabase.service;

import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.dao.DAOComputer;
import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.data.Page;
import com.excilys.computerDatabase.error.ErrorDAOComputer;
import com.excilys.computerDatabase.error.ErrorSaisieUser;

public class ServiceComputer{
	
	private static ServiceComputer INSTANCE;
	private DAOComputer database;
	
	private ServiceComputer() {
		this.database = DAOComputer.getInstance();
	}
	
	public static synchronized ServiceComputer getInstance() {
		if(ServiceComputer.INSTANCE == null) {
			ServiceComputer.INSTANCE = new ServiceComputer();
		}
	return ServiceComputer.INSTANCE;
	}
	
	public Optional<Computer> getComputer(int id) throws ErrorSaisieUser {
		return this.database.getComputer(id);
	}
	
	public List<Computer> getListComputer(Page page) throws ErrorSaisieUser {
		return this.database.getListComputer(page);	
	}
		
	public long addComputer(Computer computer){
		return this.database.insertComputer(computer);
	}
	
	public void updateComputer(Computer computer) {
		this.database.updateComputer(computer);
	}
	
	public void deleteComputerById(int id) {
		if(id != -1) {
			this.database.deleteComputerById(id);
		}
	}
	
	public int getNumberComputer(){
		return this.database.getNumberComputer();
	}

	public List<Computer> getSearchComputer(String search, Page page) throws ErrorSaisieUser {
		return this.database.getSearchComputer(search, page);
	}
	
	public int getSearchNumberComputer(String search) {
		return this.database.getSearchNumberComputer(search);
	}

	public List<Computer> getListComputerOrder(String orderField, String sort, Page page) throws  ErrorSaisieUser {
		return this.database.getListComputerOrder(orderField, sort, page);
	}
	
	public int getNumberComputerOrder(String orderField, String sort ) {
		return this.database.getNumberComputerOrder(orderField, sort);
	}
	
	public List<Computer> getResearchComputerOrder(String search, String orderField, String sort, Page page) {
		return this.database.getSearchComputerOrder(search, orderField, sort, page);
	}
	
	public int getSearchNumberComputerOrder(String search, String orderField, String sort) {
		return this.database.getSearchNumberComputerOrder(search, orderField, sort);
	}
}
