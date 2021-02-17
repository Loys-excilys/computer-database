package com.excilys.computerDatabase.view;

import java.util.List;

import com.excilys.computerDatabase.controller.Controller;
import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.service.Service;

public class ViewCompany extends View{


	public ViewCompany(Service service, Controller controller) {
		super(service);
		this.controller = controller;
	}
	
	public void printListCompany(List<Company> listCompany) {
		boolean next = true;
		int page = 0;
		while(next) {
			for(int i = 0; i < listCompany.size(); i++) {
				System.out.println("Name : " + listCompany.get(i).getName());
			}
			if (next = this.printAskEntryBoolean("continue ? (y or n) : ")) {
				listCompany = this.service.getServiceCompany().getListCompany(++page);
			}
			this.space();
		}
	}
	
}
