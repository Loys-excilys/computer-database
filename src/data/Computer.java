package data;

import java.sql.Date;

public class Computer {
	
	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private String company;
	
	public Computer(int id, String name, Date introduced, Date discontinued, String company) {
		this.setId(id);
		this.setName(name);
		this.setIntroduced(introduced);
		this.setDiscontinued(discontinued);
		this.setCompany(company);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
	public Date getIntroduced() {
		return this.introduced;
	}
	
	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}
	public Date getDiscontinued() {
		return this.discontinued;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompany() {
		return this.company;
	}
}
