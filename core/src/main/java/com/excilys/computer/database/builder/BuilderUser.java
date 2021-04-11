package com.excilys.computer.database.builder;

import com.excilys.computer.database.data.Authorities;
import com.excilys.computer.database.data.User;

public class BuilderUser {
	private User user;
	
	public BuilderUser() {
		this.user = new User();
	}
	
	public BuilderUser addId(int id) {
		this.user.setId(id);
		return this;
	}
	
	public BuilderUser addUsername(String username) {
		this.user.setUsername(username);
		return this;
	}
	
	public BuilderUser addPassword(String password) {
		this.user.setPassword(password);
		return this;
	}
	
	public BuilderUser addEnabled(int enabled) {
		this.user.setEnabled(enabled);
		return this;
	}
	
	public BuilderUser addAuthority(Authorities authority) {
		this.user.setAuthority(authority);
		return this;
	}
	
	public User build() {
		return this.user;
	}
	
}
