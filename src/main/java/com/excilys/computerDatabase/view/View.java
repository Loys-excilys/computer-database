package com.excilys.computerDatabase.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.controller.Controller;
import com.excilys.computerDatabase.error.ErrorSaisieUser;
import com.excilys.computerDatabase.service.Service;

@Component("View")
@Scope("singleton")
public class View{

	@Autowired
	protected Controller controller;
	@Autowired
	protected Service service;
	@Autowired
	private ViewComputer viewComputer;
	@Autowired
	private ViewCompany viewCompany;
	
	protected Scanner saisieUser = new Scanner(System.in);
	
	public void cli(){
		boolean boucle = true;
		
		while(boucle) {
		
			int commande = this.printAskEntryInt(" 1 : Lister les ordinateurs"
					+ "\n 2 : Lister les companies"
					+ "\n 3 : Afficher détail d'un ordinateur"
					+ "\n 4 : Création d'un ordinateur"
					+ "\n 5 : Update d'un ordinateur"
					+ "\n 6 : Delete d'un ordianteur"
					+ "\n 7 : Delete d'un company"
					+ "\n 0 : éteindre l'application"
					+ "\n Veuillez saisir un code d'action : ");
			
			try {
				boucle = this.controller.action(commande, boucle);
			} catch (ErrorSaisieUser exception) {
				exception.formatEntry();
			}
		}
	}
	
	protected String printAskEntryString(String message) {
		String entry;
		boolean boucle = true;
		do {
			System.out.print("\n" + message);
			entry = this.saisieUser.nextLine();
			if(entry != null || entry.compareTo("") != 0) {
				boucle = false;
			}else {
				ErrorSaisieUser error = new ErrorSaisieUser(this.getClass());
				error.formatEntry();
			}
		}while(boucle);
		
		return entry;
	}
	
	protected String printAskEntryTriChoice(String message) {
		System.out.print("\n" + message);
		String entry;
		entry = this.saisieUser.nextLine();
		if(entry.compareTo("n") == 0) {
			return "next";
		}else if(entry.compareTo("p") == 0){
			return "previous";
			
		}else if(entry.compareTo("q") != 0) {
			ErrorSaisieUser error = new ErrorSaisieUser(this.getClass());
			error.formatEntry();
		}
		return "quit";
	}
	
	protected int printAskEntryInt(String message) {
				int num = -1;
		do {
			System.out.print("\n" + message);
			try{
				num = Integer.parseInt(this.saisieUser.nextLine());
			}catch(NumberFormatException e){
				ErrorSaisieUser error = new ErrorSaisieUser(this.getClass());
				error.formatEntry();
			}
		}while(num == -1);
		
		return num;
	}
	
	protected Optional<LocalDate> printAskEntryDate(String message) {
		Optional<LocalDate> date = Optional.empty();
		boolean boucle = false;
		do {
			System.out.print("\n" + message);
			try{			
				String text = this.saisieUser.nextLine();
				if(!text.isEmpty()) {
					date = Optional.of(LocalDate.parse(text));
				}
				boucle = true;
			}catch(DateTimeParseException  e){
				ErrorSaisieUser error = new ErrorSaisieUser(this.getClass());
				error.formatEntry();
			}
		}while(!boucle);
		
		return date;
	}
		
	protected void space() {
		System.out.println("\n");
	}
	
	public ViewComputer getViewComputer() {
		return this.viewComputer;
	}
	public ViewCompany getViewCompany() {
		return this.viewCompany;
	}

}
