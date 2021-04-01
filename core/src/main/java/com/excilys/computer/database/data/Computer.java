package com.excilys.computer.database.data;

import java.time.LocalDate;
import java.util.Optional;

public class Computer {

	private long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company company;

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

	public Optional<LocalDate> getIntroduced() {
		return Optional.ofNullable(this.introduced);
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public Optional<LocalDate> getDiscontinued() {
		return Optional.ofNullable(this.discontinued);
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Optional<Company> getCompany() {
		return Optional.ofNullable(this.company);
	}
	@Override
	public String toString() {
		return "Name : " + this.getName() + ", Date introduce : "
				+ (Optional.ofNullable(this.introduced).isPresent() ? this.introduced : null) + ", Date discontinued : "
				+ (Optional.ofNullable(this.discontinued).isPresent() ? this.discontinued : null) + ", Company name : "
				+ (Optional.ofNullable(this.company).isPresent() ? this.company.getName() : null);
	}
}
