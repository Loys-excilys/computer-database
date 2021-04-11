package com.excilys.computer.database.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "UserDatabaseDTO")
@Table(name = "users")
public class UserDatabaseDTO {

	@Id
	private int id;
	private String username;
	private String password;
	private int enabled;
	private AuthoritiesDatabaseDTO authority;
	
	
	public UserDatabaseDTO(int id, String username, String password, int enabled, AuthoritiesDatabaseDTO authority) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
	}
	
	public int getId() {
		return this.id;
	}
	public void serId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public AuthoritiesDatabaseDTO getAuthority() {
		return authority;
	}
	public void setAuthority(AuthoritiesDatabaseDTO authority) {
		this.authority = authority;
	}
	
}
