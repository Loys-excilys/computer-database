package com.excilys.computerDatabase.view;

import java.util.List;

import com.excilys.computerDatabase.controller.Controller;
import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.service.Service;

public class ViewComputer extends View{

	public ViewComputer(Service service, Controller controller) {
		super(service);
		this.controller = controller;
	}
	
	public void printListComputer(List<Computer> listComputer) {
		String next = "";
		Page page = new Page();
		while(next.compareTo("quit") != 0) {
			for(int i = 0; i < listComputer.size(); i++) {
				System.out.println(listComputer.get(i).toString());
			}
			if ((next = this.printAskEntryTriChoice("(n)Next, (p)Previous or (q)Quit ? : ")).compareTo("next") == 0) {
				page.next();
				listComputer = this.service.getServiceComputer().getListComputer(page.getPage());
			}else if(next.compareTo("previous") == 0) {
				page.previous();
				listComputer = this.service.getServiceComputer().getListComputer(page.getPage());
			}
			this.space();
		}
	}
	
	public void printAskIdDetailComputer() {
		int commande = this.printAskEntryInt("Veuillez saisir l'Id de l'ordinateur : ");
		if(commande != -1) {
			this.controller.chooseIdDetailcomputer(commande);
		}
	}
	
	public void printDetailComputer(Computer computer) {
		System.out.println(computer.toString());
		this.space();
	}
	
	public void printAddComputer() {
		Computer computer = new Computer();
		computer.setName(this.printAskEntryString("Can you give me the Name ? : "));
		computer.setIntroduced(
				this.printAskEntryDate("Can you give me the date of introduce ?(yyyy-mm-dd) : "));
		computer.setDiscontinued(
				this.printAskEntryDate("Can you give me the date of discontinue ? (yyyy-mm-dd) : "));
		computer.setCompany(this.service.getServiceCompany().getCompany(this.printAskEntryString("Can you give me the name company ? : ")));
		
		this.service.getServiceComputer().addComputer(computer);
		
		System.out.println("Done");
		this.space();
	}
	
	public void printUpdateComputer() {
		Computer computer = this.service.getServiceComputer().getComputer(this.printAskEntryInt("Can you give me the computer's id ? :"));
		computer.setName(this.verifAskNewValueStringComputer(
				"What is the new name ? (actual = " + computer.getName() + ") :", computer.getName()));
		
		computer.setIntroduced(this.printAskEntryDate(
				"What is the new date of introduce ? (actual = " + computer.getIntroduced() + ") :"));

		computer.setDiscontinued(this.printAskEntryDate(
				"What is the new date of discontinued ? (actual = " + computer.getDiscontinued() + ") :"));
		
		computer.setCompany(this.verifAskNewValueCompanyComputer(
				"What is the new company owner ? (actual = " + computer.getCompany().getName() + ") :",
				computer.getCompany()));
		this.service.getServiceComputer().updateComputer(computer);
	}
	
	public void printDeleteComputer() {
		this.service.getServiceComputer().deleteComputer(this.printAskEntryInt("Can you give me the computer's id ? :"));
		System.out.println("done");
		this.space();
	}
	
	
	protected String verifAskNewValueStringComputer(String message, String actualValue) {
		String value = null;
		if((value = this.printAskEntryString(message)).compareTo("") != 0) {
			return value;
		}else {
			return actualValue;
		}
	}
	
	protected Company verifAskNewValueCompanyComputer(String message, Company actualCompany) {
		Company company = null;
		if((company = this.service.getServiceCompany().getCompany(this.printAskEntryString(message))) != null) {
			return company;
		}else {
			return actualCompany;
		}
	}
}
