package service;

import java.util.List;

import DAO.DAOComputer;
import data.Computer;

public class ServiceComputer{
	
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
		this.database.deleteComputer(id);
	}
}
