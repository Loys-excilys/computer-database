package view;

import java.util.List;

import controller.Controller;
import data.Company;
import service.ServiceCompany;
import service.ServiceComputer;

public class ViewCompany extends View{


	public ViewCompany(ServiceComputer serviceComputer, ServiceCompany serviceCompany, Controller controller) {
		super(serviceComputer, serviceCompany);
		this.controller = controller;
	}
	
	public void printListCompany(List<Company> listCompany) {
		boolean next = true;
		int page = 0;
		while(next) {
			for(int i = 0; i < listCompany.size(); i++) {
				System.out.println("Name : " + listCompany.get(i).getName());
			}
			if(this.printAskEntryString("continue ? (y or n) : ").compareTo("y") == 0){
				listCompany = this.serviceCompany.getListCompany(++page);
			}
			else {
				next = false;
			}
			this.space();
		}
	}
	
}
