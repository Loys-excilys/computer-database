package com.excilys.computerDatabase.builder;

import java.time.LocalDate;
import java.util.Optional;

import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.data.Computer;

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
	
	public ComputerBuilder addIntroduced(Optional<LocalDate> introduced) {
		this.computer.setIntroduced(introduced);
		return this;
	}
	
	public ComputerBuilder addDiscontinued(Optional<LocalDate> discontinued) {
		this.computer.setDiscontinued(discontinued);
		return this;
	}
	
	public ComputerBuilder addCompany(Optional<Company> company) {
		this.computer.setCompany(company);
		return this;
	}
	
	public Computer getComputer() {
		return this.computer;
	}
}
