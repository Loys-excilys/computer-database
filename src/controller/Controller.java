package controller;

import contract.IView;
import model.Model;
import contract.IController;
import contract.IModel;

public class Controller implements IController{
	
	private IModel model;
	private IView view;

	public Controller(IModel model, IView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public boolean action(String commande, boolean boucle) {
		switch(commande) {
		case "1" : this.view.printListComputer(this.model.listComputer());
			break;
		default : boucle = false;
		}
		
		return boucle;		
	}
}
