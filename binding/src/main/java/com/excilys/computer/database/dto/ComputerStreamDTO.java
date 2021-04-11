package com.excilys.computer.database.dto;

public class ComputerStreamDTO {

	private long id;
	private String name;
	private String introduced;
	private String discontinued;
	private CompanyStreamDTO company;

	public ComputerStreamDTO() {

	}

	public ComputerStreamDTO(long id, String name, String introduced, String discontinued, CompanyStreamDTO company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
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

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getIntroduced() {
		return this.introduced;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public String getDiscontinued() {
		return this.discontinued;
	}

	public void setCompany(CompanyStreamDTO company) {
		this.company = company;
	}

	public CompanyStreamDTO getCompany() {
		return this.company;
	}

	public String toJson() {
		return "{id:" + this.id + ",name:\"" + this.name + "\",introduced:\"" + this.introduced
				+ "\",discontinued:\"" + this.discontinued + "\",company:"
				+ (this.company != null ? this.company.toJson() : null) + "}";
	}
}