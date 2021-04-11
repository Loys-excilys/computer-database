package com.excilys.computer.database.builder;

import com.excilys.computer.database.data.Authorities;

public class BuilderAuthorities {
	private Authorities authorities;
	
	public BuilderAuthorities() {
		this.authorities = new Authorities();
	}
	
	public BuilderAuthorities addId(int id) {
		this.authorities.setId(id);
		return this;
	}
	
	public BuilderAuthorities addAthority(String authority) {
		this.authorities.setAuthority(authority);
		return this;
	}
	
	public Authorities build() {
		return this.authorities;
	}
	
}
