package controller;

import view.View;
import service.ServiceComputer;
import service.ServiceCompany;

public class Controller{
	
	private ServiceComputer serviceComputer;
	private ServiceCompany serviceCompany;
	private View view;
	
	private final int LIST_COMPUTER = 1;
	private final int LIST_COMPANY = 2;
	private final int DETAIL_COMPUTER = 3;
	private final int ADD_COMPUTER = 4;
	private final int UPDATE_COMPUTER = 5;
	private final int DELETE_COMPUTER = 6;
	private final int STOP = 0;

	public Controller(ServiceComputer serviceComputer,ServiceCompany serviceCompany, View view) {
		this.serviceComputer = serviceComputer;
		this.serviceCompany = serviceCompany;
		this.view = view;
	}

	public boolean action(int commande, boolean boucle) {
		
		
		switch(commande) {
		case LIST_COMPUTER : this.view.getViewComputer().printListComputer(this.serviceComputer.getListComputer(0));break;
		case LIST_COMPANY : this.view.getViewCompany().printListCompany(this.serviceCompany.getListCompany(0)); break;
		case DETAIL_COMPUTER : this.view.getViewComputer().printAskIdDetailComputer();break;
		case ADD_COMPUTER : this.view.getViewComputer().printAddComputer();break;
		case UPDATE_COMPUTER: this.view.getViewComputer().printUpdateComputer();break;
		case DELETE_COMPUTER : this.view.getViewComputer().printDeleteComputer(); break;
		case STOP : ;
		default : boucle = false;
		}
		
		return boucle;		
	}

	public void chooseIdDetailcomputer(int commande) {
		this.view.getViewComputer().printDetailComputer(this.serviceComputer.getComputer(commande));
	}
	
	public void changePageComputer(int page) {
		this.view.getViewComputer().printListComputer(this.serviceComputer.getListComputer(page));
	}
	
	public void changePageCompany(int page) {
		this.view.getViewCompany().printListCompany(this.serviceCompany.getListCompany(page));
	}
}
