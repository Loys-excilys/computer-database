package com.excilys.computerDatabase.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computerDatabase.config.ConfigContext;
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
		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigContext.class);
		final View view = context.getBean("View", View.class);
		view.cli();
	}

}
