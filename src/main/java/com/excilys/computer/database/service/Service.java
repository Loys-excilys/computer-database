package com.excilys.computer.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {
	
	@Autowired
	private ServiceCompany serviceCompany;
	@Autowired
	private ServiceComputer serviceComputer;
	
	public ServiceCompany getServiceCompany() {
		return this.serviceCompany;
	}
	
	public ServiceComputer getServiceComputer() {
		return this.serviceComputer;
	}
}
