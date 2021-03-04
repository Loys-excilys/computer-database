package com.excilys.computerDatabase.controller;

import com.excilys.computerDatabase.data.Page;
import com.excilys.computerDatabase.error.ErrorDAOCompany;
import com.excilys.computerDatabase.error.ErrorDAOComputer;
import com.excilys.computerDatabase.error.ErrorSaisieUser;
import com.excilys.computerDatabase.service.Service;
import com.excilys.computerDatabase.view.View;

public class Controller{
	
	private Service service;
	private View view;
	
	private final int LIST_COMPUTER = 1;
	private final int LIST_COMPANY = 2;
	private final int DETAIL_COMPUTER = 3;
	private final int ADD_COMPUTER = 4;
	private final int UPDATE_COMPUTER = 5;
	private final int DELETE_COMPUTER = 6;
	private final int DELETE_COMPANY = 7;
	private final int STOP = 0;

	public Controller(Service service, View view) {
		this.service = service;
		this.view = view;
	}

	public boolean action(int commande, boolean boucle) throws ErrorSaisieUser{
		Page page = new Page();
		
		switch(commande) {
		case LIST_COMPUTER : this.view.getViewComputer().printListComputer(this.service.getServiceComputer().getListComputer(page));break;
		case LIST_COMPANY : this.view.getViewCompany().printListCompany(this.service.getServiceCompany().getListCompany(0)); break;
		case DETAIL_COMPUTER : this.view.getViewComputer().printAskIdDetailComputer();break;
		case ADD_COMPUTER : this.view.getViewComputer().printAddComputer();break;
		case UPDATE_COMPUTER: this.view.getViewComputer().printUpdateComputer();break;
		case DELETE_COMPUTER : this.view.getViewComputer().printDeleteComputer(); break;
		case DELETE_COMPANY : this.view.getViewCompany().printDeleteCompany(); break;
		case STOP : ;
		default : boucle = false;
		}
		
		return boucle;		
	}

	public void chooseIdDetailcomputer(int commande) throws ErrorSaisieUser {
		this.view.getViewComputer().printDetailComputer(this.service.getServiceComputer().getComputer(commande));
	}
	
	public void changePageComputer(Page page) throws ErrorSaisieUser {
		this.view.getViewComputer().printListComputer(this.service.getServiceComputer().getListComputer(page));
	}
	
	public void changePageCompany(int page){
		this.view.getViewCompany().printListCompany(this.service.getServiceCompany().getListCompany(page));
	}
}
