package com.excilys.computer.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.computer.database.dao.DAOUser;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.data.User;
import com.excilys.computer.database.error.ErrorSaisieUser;

@Service
public class ServiceUser {

	private DAOUser database;
	
	public ServiceUser(DAOUser dao) {
		this.database = dao;
	}
	
	public void addUser(User user) {
		this.database.addUser(user);
	}
	
	public User getUserByUsername(String Username) throws ErrorSaisieUser {
		return this.database.getUserByName(Username);
	}
	
	public List<User> getUserList(){
		return this.database.getUserList();
	}
	
	public List<User> getUserList(Page page){
		return this.database.getListUser(page);
	}
	
	public void updateUser(User user) throws ErrorSaisieUser {
		this.database.updateUser(user);
	}
	
	public void deleteUser(int id) throws ErrorSaisieUser {
		this.database.deleteUser(id);
	}
	
	public boolean login(String username, String password) throws ErrorSaisieUser {
		return this.database.login(username, password);
	}
	
}
