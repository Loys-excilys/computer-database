package com.excilys.computerDatabase.service;

public class Service {
	
	private ServiceCompany serviceCompany;
	private ServiceComputer serviceComputer;
	
	public Service() {}
	
	public void createService() {
		this.serviceCompany = new ServiceCompany();
		this.serviceComputer = new ServiceComputer();
	}
	
	public ServiceCompany getServiceCompany() {
		return this.serviceCompany;
	}
	
	public ServiceComputer getServiceComputer() {
		return this.serviceComputer;
	}
}
