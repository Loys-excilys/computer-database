package view;

import java.util.Scanner;


import data.Company;
import service.ServiceCompany;
import service.ServiceComputer;
import controller.Controller;

public class View{

	protected Controller controller;
	protected ServiceComputer serviceComputer;
	protected ServiceCompany serviceCompany;
	private ViewComputer viewComputer;
	private ViewCompany viewCompany;
	
	private Scanner saisieUser = new Scanner(System.in);
	
	public View(ServiceComputer serviceComputer, ServiceCompany serviceCompany) {
		this.serviceComputer = serviceComputer;
		this.serviceCompany = serviceCompany;		
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
		this.viewCompany = new ViewCompany(serviceComputer, serviceCompany, controller);
		this.viewComputer = new ViewComputer(serviceComputer, serviceCompany, controller);
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
		if((company = this.serviceCompany.getCompany(this.printAskEntryString(message))) != null) {
			return company;
		}else {
			return actualCompany;
		}
	}
	
	protected String printAskEntryString(String message) {
		System.out.print(message);
		return this.saisieUser.next();
	}
	
	protected int printAskEntryInt(String message) {
		System.out.print(message);
		return this.saisieUser.nextInt();
	}
		
	protected void space() {
		System.out.println("\n\n\n");
	}
	
	public ViewComputer getViewComputer() {
		return this.viewComputer;
	}
	public ViewCompany getViewCompany() {
		return this.viewCompany;
	}

}
