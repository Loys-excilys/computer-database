package view;

import java.util.List;
import java.util.Scanner;

import contract.IController;
import contract.IModel;
import contract.IView;
import model.DataComputer;

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
		while(boucle) {
		
			Scanner saisieUtilisateur = new Scanner(System.in);
	
			System.out.println("Veuillez saisir un mot :");
			String commande = saisieUtilisateur.next();
			
			boucle = this.controller.action(commande, boucle);
		}
	}

	public void printListComputer(List<DataComputer> listComputer) {
		for(int i = 0; i < listComputer.size(); i++) {
			System.out.println("Name : " + listComputer.get(i).getName());
		}
	}
}
