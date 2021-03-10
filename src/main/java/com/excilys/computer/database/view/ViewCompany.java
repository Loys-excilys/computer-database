package com.excilys.computer.database.view;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Page;

@Component
public class ViewCompany extends View{

	
	public void printListCompany(List<Company> listCompany) {
		String next = "";
		Page page = new Page();
		while(next.compareTo("quit") != 0) {
			for(int i = 0; i < listCompany.size(); i++) {
				System.out.println(listCompany.get(i).toString());
			}
			next = this.printAskEntryTriChoice("(n)Next, (p)Previous or (q)Quit ? : ");
			if(next.compareTo("quit") != 0) {
				if (next.compareTo("next") == 0) {
					page.next();
				}else if(next.compareTo("previous") == 0) {
					page.previous();
				}
				listCompany = this.service.getServiceCompany().getListCompany(page.getPage());
				if(listCompany.isEmpty()) {
					page.previous();
				}
			}
			
			this.space();
		}
	}

	public void printDeleteCompany(){
		int idCompanySelected = this.printAskEntryInt("Enter the id of the company : ");
		this.service.getServiceCompany().deleteCompanyById(idCompanySelected);
		System.out.println("done");
		this.space();
	}
	
}
