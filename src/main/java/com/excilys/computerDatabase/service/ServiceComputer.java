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
	
	public Optional<Computer> getComputer(int id) throws ErrorDAOComputer, ErrorSaisieUser {
		return this.database.getComputer(id);
	}
	
	public List<Computer> getListComputer(Page page) throws ErrorDAOComputer, ErrorSaisieUser {
		return this.database.getListComputer(page);	
	}
		
	public long addComputer(Computer computer) throws ErrorDAOComputer {
		return this.database.insertComputer(computer);
	}
	
	public void updateComputer(Computer computer) throws ErrorDAOComputer {
		this.database.updateComputer(computer);
	}
	
	public void deleteComputerById(int id) throws ErrorDAOComputer {
		if(id != -1) {
			this.database.deleteComputerById(id);
		}
	}
	
	public int getNumberComputer() throws ErrorDAOComputer{
		return this.database.getNumberComputer();
	}

	public List<Computer> getResearchComputer(String search, Page page) throws ErrorDAOComputer, ErrorSaisieUser {
		return this.database.getSearchComputer(search, page);
	}

	public List<Computer> getListComputerOrder(String orderField, String sort, Page page) throws ErrorDAOComputer, ErrorSaisieUser {
		return this.database.getListComputerOrder(orderField, sort, page);
	}
}
