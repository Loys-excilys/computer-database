package com.excilys.computer.database.dto;

public class AuthoritiesStreamDTO {

	private int id;
	private String authority;
	
	public AuthoritiesStreamDTO() {}
	
	public AuthoritiesStreamDTO(int id, String authority) {
		this.id = id;
		this.authority = authority;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
