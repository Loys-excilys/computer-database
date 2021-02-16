package view;

import java.time.LocalDate;
import java.util.List;

import controller.Controller;
import data.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

public class ViewComputer extends View{

	public ViewComputer(ServiceComputer serviceComputer, ServiceCompany serviceCompany, Controller controller) {
		super(serviceComputer, serviceCompany);
		this.controller = controller;
	}
	
	public void printListComputer(List<Computer> listComputer) {
		boolean next = true;
		int page = 0;
		while(next) {
			for(int i = 0; i < listComputer.size(); i++) {
				System.out.println("Name : " + listComputer.get(i).getName());
			}
			if(this.printAskEntryString("continue ? (y or n) : ").compareTo("y") == 0){
				listComputer = this.serviceComputer.getListComputer(++page);
			}
			else {
				next = false;
			}
			this.space();
		}
	}
	
	public void printAskIdDetailComputer() {
		int commande = this.printAskEntryInt("Veuillez saisir l'Id de l'ordinateur : ");
		this.controller.chooseIdDetailcomputer(commande);
	}
	
	public void printDetailComputer(Computer computer) {
		System.out.println("Name : " + computer.getName() 
		+ ", Date introduce : " + computer.getIntroduced() 
		+ ", Date discontinued : " + computer.getDiscontinued()
		+ ", Company name : " + computer.getCompany().getName());
		this.space();
	}
	
	public void printAddComputer() {
		Computer computer = new Computer();
		computer.setName(this.printAskEntryString("Can you give me the Name ? : "));
		computer.setIntroduced(LocalDate.parse(
				this.printAskEntryString("Can you give me the date of introduce ?(yyyy-mm-dd) : ")));
		computer.setDiscontinued(LocalDate.parse(
				this.printAskEntryString("Can you give me the date of discontinue ? (yyyy-mm-dd) : ")));
		computer.setCompany(this.serviceCompany.getCompany(this.printAskEntryString("Can you give me the name company ? : ")));
		
		this.serviceComputer.addComputer(computer);
		
		System.out.println("Done");
		this.space();
	}
	
	public void printUpdateComputer() {
		Computer computer = this.serviceComputer.getComputer(this.printAskEntryInt("Can you give me the computer's id ? :"));
		computer.setName(this.verifAskNewValueStringComputer(
				"What is the new name ? (actual = " + computer.getName() + ") :", computer.getName()));
		
		computer.setIntroduced(LocalDate.parse(this.verifAskNewValueStringComputer(
				"What is the new date of introduce ? (actual = " + computer.getIntroduced() + ") :",
				computer.getIntroduced().toString())));
		
		computer.setDiscontinued(LocalDate.parse(this.verifAskNewValueStringComputer(
				"What is the new date of discontinued ? (actual = " + computer.getDiscontinued() + ") :",
				computer.getDiscontinued().toString())));
		
		computer.setCompany(this.verifAskNewValueCompanyComputer(
				"What is the new company owner ? (actual = " + computer.getCompany().getName() + ") :",
				computer.getCompany()));
		this.serviceComputer.updateComputer(computer);
	}
	
	public void printDeleteComputer() {
		this.serviceComputer.deleteComputer(this.printAskEntryInt("Can you give me the computer's id ? :"));
		System.out.println("done");
		this.space();
	}
}
