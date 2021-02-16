package controller;

import contract.IView;
import contract.IController;
import contract.IModel;

public class Controller implements IController{
	
	private IModel model;
	private IView view;

	public Controller(IModel model, IView view) {
		this.model = model;
		this.view = view;
	}

	public boolean action(int commande, boolean boucle) {
		switch(commande) {
		case 1 : this.view.printListComputer(this.model.getListComputer());break;
		case 2 : this.view.printListCompany(this.model.getListCompany()); break;
		case 3 : this.view.printAskIdDetailComputer();break;
		case 4 : this.view.printAddComputer();break;
		case 5 : this.view.printUpdateComputer();break;
		case 6 : this.view.printDeleteComputer(); break;
		case 0 : ;
		default : boucle = false;
		}
		
		return boucle;		
	}

	public void chooseIdDetailcomputer(int commande) {
		this.view.printDetailComputer(this.model.getComputer(commande));
	}
}
