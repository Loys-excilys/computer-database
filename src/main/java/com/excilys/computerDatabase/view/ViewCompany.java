package com.excilys.computerDatabase.view;

import java.util.List;

import com.excilys.computerDatabase.controller.Controller;
import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.data.Page;
import com.excilys.computerDatabase.error.ErrorDAOCompany;
import com.excilys.computerDatabase.service.Service;

public class ViewCompany extends View{


	public ViewCompany(Service service, Controller controller) {
		super(service);
		this.controller = controller;
	}
	
	public void printListCompany(List<Company> listCompany) {
		String next = "";
		Page page = new Page();
		while(next.compareTo("quit") != 0) {
			for(int i = 0; i < listCompany.size(); i++) {
				System.out.println(listCompany.get(i).toString());
			}
			if((next = this.printAskEntryTriChoice("(n)Next, (p)Previous or (q)Quit ? : ")).compareTo("quit") != 0) {
				if (next.compareTo("next") == 0) {
					page.next();
				}else if(next.compareTo("previous") == 0) {
					page.previous();
				}
				try {
					listCompany = this.service.getServiceCompany().getListCompany(page.getPage());
					if(listCompany.size() == 0) {
						page.previous();
					}
				} catch (ErrorDAOCompany errorConnection) {
					errorConnection.connectionLost();
				}
			}
			
			this.space();
		}
	}
	
}
