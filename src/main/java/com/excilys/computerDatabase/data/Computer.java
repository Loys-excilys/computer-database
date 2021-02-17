package com.excilys.computerDatabase.data;


import java.time.LocalDate;

public class Computer {
	
	private long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company company;
	
	public Computer(int id, String name, LocalDate introduced, LocalDate discontinued, Company company) {
		this.setId(id);
		this.setName(name);
		this.setIntroduced(introduced);
		this.setDiscontinued(discontinued);
		this.setCompany(company);
	}

	public Computer() {}

	public Computer(int id, String name, Object introduced, Object discontinued, Company company) {
		this.setId(id);
		this.setName(name);
		this.setIntroduced((LocalDate) introduced);
		this.setDiscontinued((LocalDate) discontinued);
		this.setCompany(company);
	}

	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	public LocalDate getIntroduced() {
		return this.introduced;
	}
	
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}
	public LocalDate getDiscontinued() {
		return this.discontinued;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	public Company getCompany() {
		return this.company;
	}
	
	public String toString() {
		return "Name : " + this.getName() 
		+ ", Date introduce : " + this.getIntroduced() 
		+ ", Date discontinued : " + this.getDiscontinued()
		+ ", Company name : " + this.getCompany().getName();
	}
}
