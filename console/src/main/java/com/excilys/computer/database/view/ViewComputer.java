package com.excilys.computer.database.view;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computer.database.builder.BuilderComputer;
import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.stream.httpStream;

@Component
public class ViewComputer extends View {

	@Autowired
	protected httpStream stream;

	public void printListComputer(List<Computer> listComputer) {
		String actionPage = "";
		Page page = new Page();
		while (actionPage.compareTo("quit") != 0) {
			for (int i = 0; i < listComputer.size(); i++) {
				System.out.println(listComputer.get(i).toString());
			}
			actionPage = this.printAskEntryTriChoice("(n)Next, (p)Previous or (q)Quit ? : ");
			if (actionPage.compareTo("quit") != 0) {
				if (actionPage.compareTo("next") == 0) {
					page.next();
				} else if (actionPage.compareTo("previous") == 0) {
					page.previous();
				}
				try {
					try {
						listComputer = this.stream.getComputerListStream(page);
					} catch (JSONException | IOException e) {
						e.printStackTrace();
					}
					if (listComputer.isEmpty()) {
						page.previous();
					}
				} catch (ErrorSaisieUser exception) {
					exception.formatEntry();
				}
			}
			this.space();
		}
	}

	public void printAskIdDetailComputer() {
		int commande = this.printAskEntryInt("Veuillez saisir l'Id de l'ordinateur : ");
		if (commande != -1) {
			try {
				this.controller.chooseIdDetailcomputer(commande);
			} catch (ErrorSaisieUser exception) {
				exception.formatEntry();
			}
		}
	}

	public void printDetailComputer(Optional<Computer> computer) {
		System.out.print(computer.isPresent() ? computer.get() : "");
		this.space();
	}

	public void printAddComputer() {
		Computer computer = null;
		try {
			computer = new BuilderComputer().addName(this.printAskEntryString("Can you give me the Name ? : "))
					.addIntroduced(this.printAskEntryDate("Can you give me the date of introduce ?(yyyy-mm-dd) : "))
					.addDiscontinued(
							this.printAskEntryDate("Can you give me the date of discontinue ? (yyyy-mm-dd) : "))
					.addCompany(this.stream
							.getCompanyStream(
									this.printAskEntryString("Can you give me the name company ? : ")).orElse(null))
					.build();
		} catch (JSONException | IOException | ErrorSaisieUser e) {
			e.printStackTrace();
		}
		try {
			this.stream.addComputerStream(computer);
		} catch (JSONException | IOException | ErrorSaisieUser e) {
			e.printStackTrace();
		}
		this.space();
	}

	public void printUpdateComputer() {
		Optional<Computer> optionalComputer = Optional.empty();
		try {
			optionalComputer = this.stream
							.getComputerStream(
									this.printAskEntryInt("Can you give me the computer's id ? :"));
		} catch (ErrorSaisieUser exception) {
			exception.formatEntry();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (optionalComputer.isPresent()) {
			Computer computer = optionalComputer.get();
			computer.setName(this.verifAskNewValueStringComputer(
					"What is the new name ? (actual = " + computer.getName() + ") :", computer.getName()));

			computer.setIntroduced(this.printAskEntryDate(
					"What is the new date of introduce ? (actual = " + computer.getIntroduced().get() + ") :"));

			computer.setDiscontinued(this.printAskEntryDate(
					"What is the new date of discontinued ? (actual = " + computer.getDiscontinued().get() + ") :"));

			computer.setCompany(this.verifAskNewValueCompanyComputer(
					"What is the new company owner ? (actual = " + computer.getCompany().get().getName() + ") :",
					computer.getCompany()));
			try {
				this.stream.updateComputerStream(computer);
			} catch (JSONException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void printDeleteComputer() {
		try {
			this.stream.deleteComputerById(this.printAskEntryInt("Can you give me the computer's id ? :"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("done");

		this.space();
	}

	protected String verifAskNewValueStringComputer(String message, String actualValue) {
		String value = this.printAskEntryString(message);
		if (value.compareTo("") != 0) {
			return value;
		} else {
			return actualValue;
		}
	}

	protected Company verifAskNewValueCompanyComputer(String message, Optional<Company> oldCompany) {
		Company company = null;
		try {
			company = this.stream.getCompanyListStream(this.printAskEntryInt(message)).get(0);
		} catch (JSONException | IOException | ErrorSaisieUser e) {
			e.printStackTrace();
		}
		if (company != null) {
			return company;
		} else {
			return oldCompany.get();
		}
	}
}
