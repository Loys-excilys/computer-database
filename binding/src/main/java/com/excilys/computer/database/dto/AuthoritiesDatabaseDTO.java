package com.excilys.computer.database.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "AuthoritiesDatabaseDTO")
@Table(name = "authorities")
public class AuthoritiesDatabaseDTO {
	
	@Id
	private int id;
	private String authority;
	
	public AuthoritiesDatabaseDTO() {}
	
	public AuthoritiesDatabaseDTO(int id, String authority) {
		this.id = id;
		this.authority = authority;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
