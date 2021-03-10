package com.excilys.computer.database.validator;

import java.time.LocalDate;
import java.util.Optional;

import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.error.ErrorSaisieUser;

public class ValidateurComputer {

	private static ValidateurComputer INSTANCE;

	private ValidateurComputer() {
	}

	public static synchronized ValidateurComputer getInstance() {
		if (ValidateurComputer.INSTANCE == null) {
			ValidateurComputer.INSTANCE = new ValidateurComputer();
		}
		return ValidateurComputer.INSTANCE;
	}

	public Computer getValidate(Computer computer) throws ErrorSaisieUser {
		valideName(computer);
		valideDate(computer);
		return computer;
	}

	public void valideName(Computer computer) throws ErrorSaisieUser {
		if (computer.getName().trim().compareTo("") == 0 || computer.getName() == null) {
			throw new ErrorSaisieUser(this.getClass());
		}
	}

	public void valideDate(Computer computer) throws ErrorSaisieUser {
		Optional<LocalDate> introduced = computer.getIntroduced();
		Optional<LocalDate> discontinued = computer.getDiscontinued();
		if (introduced.isPresent() && discontinued.isPresent() && !introduced.get().isBefore(discontinued.get())) {
			throw new ErrorSaisieUser(this.getClass());
		}
	}

}
