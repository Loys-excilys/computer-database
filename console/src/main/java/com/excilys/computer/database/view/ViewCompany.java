package com.excilys.computer.database.view;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.service.ServiceCompany;
import com.excilys.computer.database.stream.httpStream;

@Component
public class ViewCompany extends View {

	@Autowired
	private httpStream stream;

	public void printListCompany(List<Company> listCompany) {
		String next = "";
		Page page = new Page();
		while (next.compareTo("quit") != 0) {
			for (int i = 0; i < listCompany.size(); i++) {
				System.out.println(listCompany.get(i).toString());
			}
			next = this.printAskEntryTriChoice("(n)Next, (p)Previous or (q)Quit ? : ");
			if (next.compareTo("quit") != 0) {
				if (next.compareTo("next") == 0) {
					page.next();
				} else if (next.compareTo("previous") == 0) {
					page.previous();
				}
				try {
					listCompany = this.stream.getCompanyListStream("page/" + page.getPage());
				} catch (JSONException | IOException | ErrorSaisieUser e) {
					e.printStackTrace();
				}
				if (listCompany.isEmpty()) {
					page.previous();
				}
			}
			this.space();
		}
	}

	public void printDeleteCompany() {
		int idCompanySelected = this.printAskEntryInt("Enter the id of the company : ");
		this.stream.deleteCompanyById(idCompanySelected);
		System.out.println("done");
		this.space();
	}

}
