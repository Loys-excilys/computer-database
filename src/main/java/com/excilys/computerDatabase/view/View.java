package com.excilys.computerDatabase.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.excilys.computerDatabase.controller.Controller;
import com.excilys.computerDatabase.service.Service;

public class View{

	protected Controller controller;
	protected Service service;
	private ViewComputer viewComputer;
	private ViewCompany viewCompany;
	
	protected Scanner saisieUser = new Scanner(System.in);
	
	public View(Service service) {
		this.service = service;		
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
		this.viewCompany = new ViewCompany(service, controller);
		this.viewComputer = new ViewComputer(service, controller);
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
	
	protected String printAskEntryString(String message) {
		System.out.print(message);
		String entry = null;
		entry = this.saisieUser.nextLine();
		return entry;
	}
	
	protected Boolean printAskEntryBoolean(String message) {
		System.out.print(message);
		String entry = null;
		entry = this.saisieUser.next();
		if(entry.compareTo("y") == 0) {
			return true;
		}else if(entry.compareTo("n") != 0) {
			System.out.println("Entrée incorrect, l'affichage va s'arréter");
		}
		return false;
	}
	
	protected int printAskEntryInt(String message) {
		System.out.print(message);
		int num = -1;
		try{
			num = saisieUser.nextInt();
		}catch(InputMismatchException e){
			System.out.println("Entrée incorrect");
		}
		return num;
	}
	
	protected LocalDate printAskEntryDate(String message) {
		System.out.print(message);
		LocalDate date = null;
		try{
			date = LocalDate.parse(this.saisieUser.next());
		}catch(DateTimeParseException  e){
			System.out.println("Entrée incorrect, veuiller repecter les normes de notation");
			System.exit(0);
		}
		return date;
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
