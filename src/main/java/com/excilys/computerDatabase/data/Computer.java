package com.excilys.computerDatabase.data;


import java.time.LocalDate;
import java.util.Optional;

public class Computer {
	
	private long id;
	private String name;
	private Optional<LocalDate> introduced;
	private Optional<LocalDate> discontinued;
	private Optional<Company> company;
	

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
	
	public void setIntroduced(Optional<LocalDate> introduced) {
		this.introduced = introduced;
	}
	public Optional<LocalDate> getIntroduced() {
		return this.introduced;
	}
	
	public void setDiscontinued(Optional<LocalDate> discontinued) {
		this.discontinued = discontinued;
	}
	public Optional<LocalDate> getDiscontinued() {
		return this.discontinued;
	}
	
	public void setCompany(Optional<Company> company) {
		this.company = company;
	}
	public Optional<Company> getCompany() {
		return this.company;
	}
	
	public String toString() {
		return "Name : " + this.getName() 
		+ ", Date introduce : " + (this.introduced.isPresent() ? this.introduced.get() : null)
		+ ", Date discontinued : " + (this.discontinued.isPresent() ? this.discontinued.get() : null)
		+ ", Company name : " + (this.company.isPresent() ? this.company.get().getName() : null );
	}
}
