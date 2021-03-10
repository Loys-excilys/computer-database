package com.excilys.computer.database.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computer.database.config.ConfigContext;
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
		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigContext.class);
		final View view = context.getBean("View", View.class);
		view.cli();
	}

}
