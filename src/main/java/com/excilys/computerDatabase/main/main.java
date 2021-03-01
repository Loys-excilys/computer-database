package com.excilys.computerDatabase.main;

import com.excilys.computerDatabase.controller.Controller;
import com.excilys.computerDatabase.service.Service;
import com.excilys.computerDatabase.view.View;

public class main {

	/**
	 * Class maim
	 * 
	 * initialise le programme
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final Service service = Service.getInstance();
		final View view = new View(service);
		final Controller controller = new Controller(service, view);
		view.setController(controller);
		view.cli();
	}

}
