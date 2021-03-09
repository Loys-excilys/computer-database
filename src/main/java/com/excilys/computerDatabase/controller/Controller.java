package com.excilys.computerDatabase.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.data.Page;
import com.excilys.computerDatabase.error.ErrorSaisieUser;
import com.excilys.computerDatabase.service.Service;
import com.excilys.computerDatabase.view.View;

@Component
@Scope("singleton")
public class Controller{
	
	@Autowired
	private Service service;
	@Resource(name="View")
	private View view;
	
	private static final int LIST_COMPUTER = 1;
	private static final int LIST_COMPANY = 2;
	private static final int DETAIL_COMPUTER = 3;
	private static final int ADD_COMPUTER = 4;
	private static final int UPDATE_COMPUTER = 5;
	private static final int DELETE_COMPUTER = 6;
	private static final int DELETE_COMPANY = 7;

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
