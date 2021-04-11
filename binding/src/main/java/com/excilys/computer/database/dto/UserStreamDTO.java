package com.excilys.computer.database.dto;

public class UserStreamDTO {

	private int id;
	private String username;
	private String password;
	private int enabled;
	private AuthoritiesStreamDTO authority;
		
	public UserStreamDTO(int id, String username, String password, int enabled, AuthoritiesStreamDTO authority) {
		this.setId(id);
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
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
	public AuthoritiesStreamDTO getAuthority() {
		return authority;
	}
	public void setAuthority(AuthoritiesStreamDTO authority) {
		this.authority = authority;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
