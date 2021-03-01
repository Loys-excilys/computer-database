package com.excilys.computerDatabase.service;

public class Service {
	
	private static Service INSTANCE;
	private ServiceCompany serviceCompany;
	private ServiceComputer serviceComputer;
	
	private Service() {
		this.createService();
	}
	
	public static synchronized Service getInstance() {
		if(Service.INSTANCE == null) {
			Service.INSTANCE = new Service();
		}
	return Service.INSTANCE;
	}
	
	public void createService() {
		this.serviceCompany = ServiceCompany.getInstance();
		this.serviceComputer = ServiceComputer.getInstance();
	}
	
	public ServiceCompany getServiceCompany() {
		return this.serviceCompany;
	}
	
	public ServiceComputer getServiceComputer() {
		return this.serviceComputer;
	}
}
