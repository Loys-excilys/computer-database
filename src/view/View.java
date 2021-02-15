package view;

import java.sql.Date;
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
	
	public View(IModel model) {
		this.model = model;
	}
	
	public void setController(IController controller) {
		this.controller = controller;
	}
	
	public void cli() {
		boolean boucle = true;
		Scanner saisieUtilisateur = new Scanner(System.in);
		while(boucle) {
		
			System.out.println("1 : Lister les ordinateurs");
			System.out.println("2 : Lister les companies");
			System.out.println("3 : Afficher détail d'un ordinateur");
			System.out.println("4 : Création d'un ordinateur");
			System.out.println("0 : éteindre l'application");
			System.out.print("Veuillez saisir un code d'action :");
			int commande = saisieUtilisateur.nextInt();
			
			boucle = this.controller.action(commande, boucle);
		}
	}

	public void printListComputer(List<Computer> listComputer) {
		for(int i = 0; i < listComputer.size(); i++) {
			System.out.println("Name : " + listComputer.get(i).getName());
		}
		this.space();
	}

	public void printListCompany(List<Company> listCompany) {
		for(int i = 0; i < listCompany.size(); i++) {
			System.out.println("Name : " + listCompany.get(i).getName());
		}
		this.space();
	}
	
	public void askIdDetailComputer() {
		Scanner saisieUtilisateur = new Scanner(System.in);
		System.out.println("Veuillez saisir l'Id de l'ordinateur :");
		int commande = saisieUtilisateur.nextInt();
		
		this.controller.chooseIdDetailcomputer(commande);
	}
	
	public void printDetailComputer(Computer computer) {
		System.out.println("Name : " + computer.getName() 
		+ ", Date introduce : " + computer.getIntroduced() 
		+ ", Date discontinued : " + computer.getDiscontinued()
		+ ", Company name : " + computer.getCompany());
		this.space();
	}
	
	public void addComputer() {
		Computer computer = new Computer();
		Scanner saisieUtilisateur = new Scanner(System.in);
		System.out.print("Can you give me the Name ? : ");
		computer.setName(saisieUtilisateur.next());
		System.out.print("Can you give me the date of introduce ?(yyyy-mm-dd) : ");
		computer.setIntroduced(Date.valueOf(saisieUtilisateur.next()));
		System.out.print("Can you give me the date of discontinue ? (yyyy-mm-dd) : ");
		computer.setDiscontinued(Date.valueOf(saisieUtilisateur.next()));
		System.out.print("Can you give me the company id ? : ");
		computer.setCompanyId(saisieUtilisateur.nextInt());
		
		this.model.addComputer(computer);
		
		System.out.println("Done");
		this.space();
	}
	
	public void space() {
		System.out.println("\n\n\n");
	}
}
