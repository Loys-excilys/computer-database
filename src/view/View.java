package view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import contract.IController;
import contract.IModel;
import contract.IView;
import data.Company;
import data.Computer;

public class View implements IView{

	private IController controller;
	private IModel model;
	
	private Scanner saisieUser = new Scanner(System.in);
	
	public View(IModel model) {
		this.model = model;
	}
	
	public void setController(IController controller) {
		this.controller = controller;
	}
	
	public void cli() {
		boolean boucle = true;
		
		while(boucle) {
		
			int commande = this.printAskEntryInt(" 1 : Lister les ordinateurs"
					+ "\n 2 : Lister les companies"
					+ "\n 3 : Afficher détail d'un ordinateur"
					+ "\n 4 : Création d'un ordinateur"
					+ "\n 5 : Update d'un ordinateur"
					+ "\n 6 : Delete d'un ordianteur"
					+ "\n 0 : éteindre l'application"
					+ "\n Veuillez saisir un code d'action : ");
			
			boucle = this.controller.action(commande, boucle);
		}
	}

	public void printListComputer(List<Computer> listComputer) {
		boolean next = true;
		while(next) {
			for(int i = 0; i < listComputer.size(); i++) {
				System.out.println("Name : " + listComputer.get(i).getName());
			}
			if(this.printAskEntryString("continue ? (y or n) : ").compareTo("y") == 0){
				listComputer = this.model.getListComputer();
			}
			else {
				next = false;
			}
			this.space();
		}
	}

	public void printListCompany(List<Company> listCompany) {
		boolean next = true;
		while(next) {
			for(int i = 0; i < listCompany.size(); i++) {
				System.out.println("Name : " + listCompany.get(i).getName());
			}
			if(this.printAskEntryString("continue ? (y or n) : ").compareTo("y") == 0){
				listCompany = this.model.getListCompany();
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
		computer.setCompany(this.model.getCompany(this.printAskEntryString("Can you give me the name company ? : ")));
		
		this.model.addComputer(computer);
		
		System.out.println("Done");
		this.space();
	}
	
	public void printUpdateComputer() {
		Computer computer = this.model.getComputer(this.printAskEntryInt("Can you give me the computer's id ? :"));
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
		this.model.updateComputer(computer);
	}
	
	public void printDeleteComputer() {
		this.model.deleteComputer(this.printAskEntryInt("Can you give me the computer's id ? :"));
		System.out.println("done");
		this.space();
	}
	
	
	
	
	
	public String verifAskNewValueStringComputer(String message, String actualValue) {
		String value = null;
		if((value = this.printAskEntryString(message)).compareTo("") != 0) {
			return value;
		}else {
			return actualValue;
		}
	}
	
	public Company verifAskNewValueCompanyComputer(String message, Company actualCompany) {
		Company company = null;
		if((company = this.model.getCompany(this.printAskEntryString(message))) != null) {
			return company;
		}else {
			return actualCompany;
		}
	}
	
	public String printAskEntryString(String message) {
		System.out.print(message);
		return this.saisieUser.next();
	}
	
	public int printAskEntryInt(String message) {
		System.out.print(message);
		return this.saisieUser.nextInt();
	}
		
	public void space() {
		System.out.println("\n\n\n");
	}

}
