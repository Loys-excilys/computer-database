package com.excilys.computer.database.dto;

public class UserStreamDTO {

	private int id;
	private String username;
	private int enabled;
	private AuthoritiesStreamDTO authority;
	
	public UserStreamDTO() {}
		
	public UserStreamDTO(int id, String username, int enabled, AuthoritiesStreamDTO authority) {
		this.setId(id);
		this.username = username;
		this.enabled = enabled;
		this.authority = authority;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
