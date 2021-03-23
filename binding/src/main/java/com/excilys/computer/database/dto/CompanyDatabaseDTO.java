package com.excilys.computer.database.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "CompanyDatabaseDTO")
@Table(name = "company")
public class CompanyDatabaseDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;

	public CompanyDatabaseDTO() {
	}

	public CompanyDatabaseDTO(long l, String name) {
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
