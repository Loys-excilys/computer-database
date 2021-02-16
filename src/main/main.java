package main;

import controller.Controller;
import service.ServiceCompany;
import service.ServiceComputer;
import view.View;

public class main {

	public static void main(String[] args) {
		final ServiceComputer serviceComputer = new ServiceComputer();
		final ServiceCompany serviceCompany = new ServiceCompany();
		final View view = new View(serviceComputer, serviceCompany);
		final Controller controller = new Controller(serviceComputer,serviceCompany, view);
		view.setController(controller);
		view.cli();
	}

}
