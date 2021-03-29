package com.excilys.computer.database.console.controller;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.service.ServiceCompany;
import com.excilys.computer.database.service.ServiceComputer;
import com.excilys.computer.database.stream.httpStream;
import com.excilys.computer.database.view.View;

@Component
public class Controller {

	private ServiceComputer serviceComputer;
	private ServiceCompany serviceCompany;
	private httpStream stream;
	private View view;

	private static final int LIST_COMPUTER = 1;
	private static final int LIST_COMPANY = 2;
	private static final int DETAIL_COMPUTER = 3;
	private static final int ADD_COMPUTER = 4;
	private static final int UPDATE_COMPUTER = 5;
	private static final int DELETE_COMPUTER = 6;
	private static final int DELETE_COMPANY = 7;

	public Controller(ServiceComputer serviceComputer, ServiceCompany serviceCompany, httpStream stream) {
		this.serviceCompany = serviceCompany;
		this.serviceComputer = serviceComputer;
		this.stream = stream;
	}

	public boolean action(int commande, boolean boucle) throws ErrorSaisieUser, JSONException, IOException {
		Page page = new Page();
		switch (commande) {
		case LIST_COMPUTER:
			this.view.getViewComputer().printListComputer(this.stream.ComputerListStream("page/1/25"));
			break;
		case LIST_COMPANY:
			this.view.getViewCompany().printListCompany(this.stream.CompanyListStream("page/0"));
			break;
		case DETAIL_COMPUTER:
			this.view.getViewComputer().printAskIdDetailComputer();
			break;
		case ADD_COMPUTER:
			this.view.getViewComputer().printAddComputer();
			break;
		case UPDATE_COMPUTER:
			this.view.getViewComputer().printUpdateComputer();
			break;
		case DELETE_COMPUTER:
			this.view.getViewComputer().printDeleteComputer();
			break;
		case DELETE_COMPANY:
			this.view.getViewCompany().printDeleteCompany();
			break;
		default:
			boucle = false;
		}
		return boucle;
	}

	public void chooseIdDetailcomputer(int commande) throws ErrorSaisieUser {
		this.view.getViewComputer().printDetailComputer(this.serviceComputer.getComputer(commande));
	}

	public void changePageComputer(Page page) throws ErrorSaisieUser {
		this.view.getViewComputer().printListComputer(this.serviceComputer.getListComputer(page));
	}

	public void changePageCompany(int page) {
		this.view.getViewCompany().printListCompany(this.serviceCompany.getListCompany(page));
	}
	
	@Autowired
	public void setView(@Qualifier("View")View view) {
		this.view = view;
	}
}
