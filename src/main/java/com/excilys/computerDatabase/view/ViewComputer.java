package com.excilys.computerDatabase.view;

import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.builder.ComputerBuilder;
import com.excilys.computerDatabase.controller.Controller;
import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.data.Page;
import com.excilys.computerDatabase.error.ErrorDAOCompany;
import com.excilys.computerDatabase.error.ErrorDAOComputer;
import com.excilys.computerDatabase.error.ErrorSaisieUser;
import com.excilys.computerDatabase.service.Service;

public class ViewComputer extends View{

	public ViewComputer(Service service, Controller controller) {
		super(service);
		this.controller = controller;
	}
	
	public void printListComputer(List<Computer> listComputer) {
		String actionPage = "";
		Page page = new Page();
		while(actionPage.compareTo("quit") != 0) {
			for(int i = 0; i < listComputer.size(); i++) {
				System.out.println(listComputer.get(i).toString());
			}
			if((actionPage = this.printAskEntryTriChoice("(n)Next, (p)Previous or (q)Quit ? : ")).compareTo("quit") != 0){
				if (actionPage.compareTo("next") == 0) {
					page.next();
				}else if(actionPage.compareTo("previous") == 0) {
					page.previous();
				}
				try {
					listComputer = this.service.getServiceComputer().getListComputer(page);
					if(listComputer.size() == 0) {
						page.previous();
					}
				}catch (ErrorSaisieUser exception) {
					exception.formatEntry();
				}
			}
			this.space();
		}
	}
	
	public void printAskIdDetailComputer() {
		int commande = this.printAskEntryInt("Veuillez saisir l'Id de l'ordinateur : ");
		if(commande != -1) {
			try {
				this.controller.chooseIdDetailcomputer(commande);
			} catch (ErrorSaisieUser exception) {
				exception.formatEntry();
			}
		}
	}
	
	public void printDetailComputer(Optional<Computer> computer) {
		System.out.print(computer.isPresent() ? computer.get(): "");
		this.space();
	}
	
	public void printAddComputer() {
		Computer computer;
		computer = new ComputerBuilder()
				.addName(this.printAskEntryString("Can you give me the Name ? : "))
				.addIntroduced(this.printAskEntryDate("Can you give me the date of introduce ?(yyyy-mm-dd) : "))
				.addDiscontinued(this.printAskEntryDate("Can you give me the date of discontinue ? (yyyy-mm-dd) : "))
				.addCompany(Optional.of(this.service.getServiceCompany().getCompany(this.printAskEntryString("Can you give me the name company ? : "))))
				.getComputer();
		this.service.getServiceComputer().addComputer(computer);		
		System.out.println("Done");		
		this.space();
	}
	
	public void printUpdateComputer(){
		Optional<Computer> optionalComputer = Optional.empty();
		try {
			optionalComputer = this.service.getServiceComputer().getComputer(this.printAskEntryInt("Can you give me the computer's id ? :"));
		} catch (ErrorSaisieUser exception) {
			exception.formatEntry();
		}
		if(optionalComputer.isPresent()) {
			Computer computer = optionalComputer.get();
			computer.setName(this.verifAskNewValueStringComputer(
					"What is the new name ? (actual = " + computer.getName() + ") :", computer.getName()));
			
			computer.setIntroduced(this.printAskEntryDate(
					"What is the new date of introduce ? (actual = " + computer.getIntroduced() + ") :"));

			computer.setDiscontinued(this.printAskEntryDate(
					"What is the new date of discontinued ? (actual = " + computer.getDiscontinued() + ") :"));
			
			computer.setCompany(this.verifAskNewValueCompanyComputer(
					"What is the new company owner ? (actual = " + computer.getCompany().get().getName() + ") :",
					computer.getCompany()));
			this.service.getServiceComputer().updateComputer(computer);
		}
	}
	
	public void printDeleteComputer() {
		this.service.getServiceComputer().deleteComputerById(this.printAskEntryInt("Can you give me the computer's id ? :"));
		System.out.println("done");
		
		this.space();
	}
	
	
	protected String verifAskNewValueStringComputer(String message, String actualValue) {
		String value;
		if((value = this.printAskEntryString(message)).compareTo("") != 0) {
			return value;
		}else {
			return actualValue;
		}
	}
	
	protected Optional<Company> verifAskNewValueCompanyComputer(String message, Optional<Company> Company){
		Optional<Company> company;
		if((company = Optional.of(this.service.getServiceCompany().getCompany(this.printAskEntryString(message)))).isPresent()) {
			return company;
		}else {
			return Company;
		}
	}
}
