package com.excilys.computer.database.builder;

import com.excilys.computer.database.data.Company;

public class BuilderCompany {
	
	private Company company;
	
	public BuilderCompany() {
		this.company = new Company();
	}
	
	public BuilderCompany addId(long id) {
		this.company.setId(id);
		return this;
	}
	
	public BuilderCompany addName(String name) {
		this.company.setName(name);
		return this;
	}
	
	public BuilderCompany addLogo(String logo) {
		this.company.setLogo(logo);
		return this;
	}
	
	public Company build() {
		return this.company;
	}

}
