package com.excilys.computer.database.view;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computer.database.builder.ComputerBuilder;
import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.service.ServiceCompany;
import com.excilys.computer.database.service.ServiceComputer;

@Component
public class ViewComputer extends View {

	@Autowired
	protected ServiceComputer serviceComputer;

	@Autowired
	protected ServiceCompany serviceCompany;

	public void printListComputer(List<Computer> listComputer) {
		String actionPage = "";
		Page page = new Page();
		while (actionPage.compareTo("quit") != 0) {
			for (int i = 0; i < listComputer.size(); i++) {
				System.out.println(listComputer.get(i).toString());
			}
			actionPage = this.printAskEntryTriChoice("(n)Next, (p)Previous or (q)Quit ? : ");
			if (actionPage.compareTo("quit") != 0) {
				if (actionPage.compareTo("next") == 0) {
					page.next();
				} else if (actionPage.compareTo("previous") == 0) {
					page.previous();
				}
				try {
					listComputer = this.serviceComputer.getListComputer(page);
					if (listComputer.isEmpty()) {
						page.previous();
					}
				} catch (ErrorSaisieUser exception) {
					exception.formatEntry();
				}
			}
			this.space();
		}
	}

	public void printAskIdDetailComputer() {
		int commande = this.printAskEntryInt("Veuillez saisir l'Id de l'ordinateur : ");
		if (commande != -1) {
			try {
				this.controller.chooseIdDetailcomputer(commande);
			} catch (ErrorSaisieUser exception) {
				exception.formatEntry();
			}
		}
	}

	public void printDetailComputer(Optional<Computer> computer) {
		System.out.print(computer.isPresent() ? computer.get() : "");
		this.space();
	}

	public void printAddComputer() {
		Computer computer;
		computer = new ComputerBuilder().addName(this.printAskEntryString("Can you give me the Name ? : "))
				.addIntroduced(this.printAskEntryDate("Can you give me the date of introduce ?(yyyy-mm-dd) : "))
				.addDiscontinued(this.printAskEntryDate("Can you give me the date of discontinue ? (yyyy-mm-dd) : "))
				.addCompany(this.serviceCompany
						.getCompany(this.printAskEntryString("Can you give me the name company ? : ")))
				.getComputer();
		this.serviceComputer.addComputer(computer);
		System.out.println("Done");
		this.space();
	}

	public void printUpdateComputer() {
		Optional<Computer> optionalComputer = Optional.empty();
		try {
			optionalComputer = this.serviceComputer
					.getComputer(this.printAskEntryInt("Can you give me the computer's id ? :"));
		} catch (ErrorSaisieUser exception) {
			exception.formatEntry();
		}
		if (optionalComputer.isPresent()) {
			Computer computer = optionalComputer.get();
			computer.setName(this.verifAskNewValueStringComputer(
					"What is the new name ? (actual = " + computer.getName() + ") :", computer.getName()));

			computer.setIntroduced(this.printAskEntryDate(
					"What is the new date of introduce ? (actual = " + computer.getIntroduced().get() + ") :"));

			computer.setDiscontinued(this.printAskEntryDate(
					"What is the new date of discontinued ? (actual = " + computer.getDiscontinued().get() + ") :"));

			computer.setCompany(this.verifAskNewValueCompanyComputer(
					"What is the new company owner ? (actual = " + computer.getCompany().get().getName() + ") :",
					computer.getCompany()));
			this.serviceComputer.updateComputer(computer);
		}
	}

	public void printDeleteComputer() {
		this.serviceComputer.deleteComputerById(this.printAskEntryInt("Can you give me the computer's id ? :"));
		System.out.println("done");

		this.space();
	}

	protected String verifAskNewValueStringComputer(String message, String actualValue) {
		String value = this.printAskEntryString(message);
		if (value.compareTo("") != 0) {
			return value;
		} else {
			return actualValue;
		}
	}

	protected Optional<Company> verifAskNewValueCompanyComputer(String message, Optional<Company> oldCompany) {
		Optional<Company> company = this.serviceCompany.getCompany(this.printAskEntryString(message));
		if (company.isPresent()) {
			return company;
		} else {
			return oldCompany;
		}
	}
}
