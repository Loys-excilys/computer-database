package com.excilys.computer.database.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computer.database.console.config.ConsoleConfigContext;
import com.excilys.computer.database.controller.Controller;
import com.excilys.computer.database.view.View;

public class main {

	/**
	 * Class maim
	 * 
	 * initialise le programme
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ConsoleConfigContext.class);
		final View view = context.getBean("View", View.class);
		//view.setController(context.getBean("Controller", Controller.class));
		view.cli();
	}

}
