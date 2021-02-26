package com.excilys.computerDatabase.data;

import java.time.LocalDate;
import java.util.Optional;

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
	
	public ComputerBuilder addIntroduced(Optional<LocalDate> Introduced) {
		this.computer.setIntroduced(Introduced);
		return this;
	}
	
	public ComputerBuilder addDiscontinued(Optional<LocalDate> Discontinued) {
		this.computer.setDiscontinued(Discontinued);
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
