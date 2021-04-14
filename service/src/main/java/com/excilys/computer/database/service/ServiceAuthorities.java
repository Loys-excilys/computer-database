package com.excilys.computer.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.computer.database.dao.DAOAuthorities;
import com.excilys.computer.database.data.Authorities;
import com.excilys.computer.database.error.ErrorSaisieUser;

@Service
public class ServiceAuthorities {

	private DAOAuthorities database;
	
	public ServiceAuthorities(DAOAuthorities dao) {
		this.database = dao;
	}
	
	public void addAuthorities(Authorities authorities){
		this.database.addAuthorities(authorities);
	}
	
	public List<Authorities> getAuthoritiesList() {
		return this.database.getAuthorities();
	}
	
	public void updateAuthorities(Authorities authorities) throws ErrorSaisieUser{
		this.database.updateAuthorities(authorities);
	}
	
	public void deleteAuthorities(int id) throws ErrorSaisieUser {
		this.database.deleteAuthorities(id);
	}
}
