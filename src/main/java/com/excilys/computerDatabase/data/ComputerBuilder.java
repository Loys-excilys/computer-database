package com.excilys.computerDatabase.data;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.excilys.computerDatabase.error.ErrorSaisieUser;

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
	
	public ComputerBuilder addIntroduced(LocalDate introduced) {
		this.computer.setIntroduced(introduced);
		return this;
	}
	
	public ComputerBuilder addDiscontinued(LocalDate Discontinued) {
		this.computer.setDiscontinued(Discontinued);
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
