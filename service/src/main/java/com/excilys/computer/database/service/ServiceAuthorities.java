package com.excilys.computer.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.computer.database.dao.DAOAuthorities;
import com.excilys.computer.database.data.Authorities;

@Service
public class ServiceAuthorities {

	private DAOAuthorities database;
	
	public ServiceAuthorities(DAOAuthorities dao) {
		this.database = dao;
	}
	
	public void addAuthorities(Authorities authorities) {
		this.database.addAuthorities(authorities);
	}
	
	public List<Authorities> getUserList(){
		return this.database.getAuthorities();
	}
	
	public void updateUser(Authorities authorities) {
		this.database.updateAuthorities(authorities);
	}
	
	public void deleteUser(int id) {
		this.database.deleteAuthorities(id);
	}
}
