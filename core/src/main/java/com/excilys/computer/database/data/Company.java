package com.excilys.computer.database.data;


public class Company {

	private long id;
	private String name;

	public Company(long l, String name) {
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
