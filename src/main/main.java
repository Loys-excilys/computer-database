package main;

import controller.Controller;
import model.Model;
import view.View;

public class main {

	public static void main(String[] args) {
		final Model model = new Model();
		final View view = new View(model);
		final Controller controller = new Controller(model, view);
		view.setController(controller);
		view.cli();

	}

}
