package com.excilys.computer.database.builder;

import java.time.LocalDate;

import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Computer;

public class ComputerBuilder {
	private Computer computer;
	
	public ComputerBuilder() {
		this.computer = new Computer();
	}
	
	public ComputerBuilder addId(long id) {
		this.computer.setId(id);
		return this;
	}
	
	public ComputerBuilder addName(String name) {
		this.computer.setName(name);
		return this;
	}
	
	public ComputerBuilder addIntroduced(LocalDate localDate) {
		this.computer.setIntroduced(localDate);
		return this;
	}
	
	public ComputerBuilder addDiscontinued(LocalDate discontinued) {
		this.computer.setDiscontinued(discontinued);
		return this;
	}
	
	public ComputerBuilder addCompany(Company company) {
		this.computer.setCompany(company);
		return this;
	}
	
	public Computer getComputer() {
		return this.computer;
	}
}
