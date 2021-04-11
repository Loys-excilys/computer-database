package com.excilys.computer.database.builder;

import java.time.LocalDate;

import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Computer;

public class BuilderComputer {
	private Computer computer;
	
	public BuilderComputer() {
		this.computer = new Computer();
	}
	
	public BuilderComputer addId(long id) {
		this.computer.setId(id);
		return this;
	}
	
	public BuilderComputer addName(String name) {
		this.computer.setName(name);
		return this;
	}
	
	public BuilderComputer addIntroduced(LocalDate localDate) {
		this.computer.setIntroduced(localDate);
		return this;
	}
	
	public BuilderComputer addDiscontinued(LocalDate discontinued) {
		this.computer.setDiscontinued(discontinued);
		return this;
	}
	
	public BuilderComputer addCompany(Company company) {
		this.computer.setCompany(company);
		return this;
	}
	
	public Computer build() {
		return this.computer;
	}
}
