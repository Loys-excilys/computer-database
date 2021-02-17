package com.excilys.computerDatabase.main;

import com.excilys.computerDatabase.controller.Controller;
import com.excilys.computerDatabase.service.Service;
import com.excilys.computerDatabase.view.View;

public class main {

	public static void main(String[] args) {
		final Service service = new Service();
		service.createService();
		final View view = new View(service);
		final Controller controller = new Controller(service, view);
		view.setController(controller);
		view.cli();
	}

}
