package model;

import java.sql.Date;

public class DataComputer {
	
	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private int companyId;
	
	public DataComputer(int id, String name, Date introduced, Date discontinued, int companyId) {
		this.setId(id);
		this.setName(name);
		this.setIntroduced(introduced);
		this.setDiscontinued(discontinued);
		this.setCompanyId(companyId);
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
	
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getCompanyId() {
		return this.companyId;
	}
}
