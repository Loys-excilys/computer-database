package com.excilys.computer.database.dto;

public class CompanyStreamDTO {

	private long id;
	private String name;

	public CompanyStreamDTO() {
	}

	public CompanyStreamDTO(long l, String name) {
		this.setId(l);
		this.setName(name);
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

	public String toString() {
		return "Name : " + this.getName();
	}
}
