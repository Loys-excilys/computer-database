package com.excilys.computer.database.dto;

public class CompanyStreamDTO {

	private long id;
	private String name;
	private String logo;

	public CompanyStreamDTO() {
	}

	public CompanyStreamDTO(long l, String name, String logo) {
		this.setId(l);
		this.setName(name);
		this.setLogo(logo);
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

	public String toJson() {
		return "{id:" + this.id + ",name:\"" + this.name + "\", logo:\"" + this.logo + "\"}";
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
}
