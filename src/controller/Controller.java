package controller;

import contract.IView;
import contract.IController;
import contract.IModel;

public class Controller implements IController{
	
	private IModel model;
	private IView view;
	
	private final int LIST_COMPUTER = 1;
	private final int LIST_COMPANY = 2;
	private final int DETAIL_COMPUTER = 3;
	private final int ADD_COMPUTER = 4;
	private final int UPDATE_COMPUTER = 5;
	private final int DELETE_COMPUTER = 6;
	private final int STOP = 0;

	public Controller(IModel model, IView view) {
		this.model = model;
		this.view = view;
	}

	public boolean action(int commande, boolean boucle) {
		
		
		switch(commande) {
		case LIST_COMPUTER : this.view.printListComputer(this.model.getListComputer());break;
		case LIST_COMPANY : this.view.printListCompany(this.model.getListCompany()); break;
		case DETAIL_COMPUTER : this.view.printAskIdDetailComputer();break;
		case ADD_COMPUTER : this.view.printAddComputer();break;
		case UPDATE_COMPUTER: this.view.printUpdateComputer();break;
		case DELETE_COMPUTER : this.view.printDeleteComputer(); break;
		case STOP : ;
		default : boucle = false;
		}
		
		return boucle;		
	}

	public void chooseIdDetailcomputer(int commande) {
		this.view.printDetailComputer(this.model.getComputer(commande));
	}
}
