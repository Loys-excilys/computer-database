package com.excilys.computer.database.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computer.database.dao.DAOComputer;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.error.ErrorSaisieUser;

@Component
public class ServiceComputer {

	@Autowired
	private DAOComputer database;

	public Optional<Computer> getComputer(int id) throws ErrorSaisieUser {
		return this.database.getComputer(id);
	}

	public List<Computer> getListComputer(Page page) throws ErrorSaisieUser {
		return this.database.getListComputer(page);
	}

	public long addComputer(Computer computer) {
		return this.database.insertComputer(computer);
	}

	public void updateComputer(Computer computer) {
		this.database.updateComputer(computer);
	}

	public void deleteComputerById(int id) {
		if (id != -1) {
			this.database.deleteComputerById(id);
		}
	}

	public int getNumberComputer() {
		return this.database.getNumberComputer();
	}

	public List<Computer> getSearchComputer(String search, Page page) throws ErrorSaisieUser {
		return this.database.getSearchComputer(search, page);
	}

	public int getSearchNumberComputer(String search) {
		return this.database.getSearchNumberComputer(search);
	}

	public List<Computer> getListComputerOrder(String orderField, String sort, Page page) throws ErrorSaisieUser {
		return this.database.getListComputerOrder(orderField, sort, page);
	}

	public List<Computer> getResearchComputerOrder(String search, String orderField, String sort, Page page)
			throws ErrorSaisieUser {
		return this.database.getSearchComputerOrder(search, orderField, sort, page);
	}
}
