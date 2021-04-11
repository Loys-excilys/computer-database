package com.excilys.computer.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.computer.database.dao.DAOUser;
import com.excilys.computer.database.data.User;

@Service
public class ServiceUser {

	private DAOUser database;
	
	public ServiceUser(DAOUser dao) {
		this.database = dao;
	}
	
	public void addUser(User user) {
		this.database.addUser(user);
	}
	
	public List<User> getUserList(){
		return this.database.getUserList();
	}
	
	public void updateUser(User user) {
		this.database.updateUser(user);
	}
	
	public void deleteUser(int id) {
		this.database.deleteUser(id);
	}
	
}
