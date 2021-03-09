package com.excilys.computerDatabase.validator;

import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.error.ErrorSaisieUser;

public class ValidateurComputer {
	
	private static ValidateurComputer INSTANCE;
	
	private ValidateurComputer() {}
	
	public static synchronized ValidateurComputer getInstance() {
		if(ValidateurComputer.INSTANCE == null) {
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
		if(computer.getName().trim().compareTo("") == 0 || computer.getName() == null) {
			throw new ErrorSaisieUser(this.getClass());
		}
	}
	
	public void valideDate(Computer computer) throws ErrorSaisieUser {
		if(computer.getIntroduced().isPresent() && computer.getDiscontinued().isPresent() && !computer.getIntroduced().get().isBefore(computer.getDiscontinued().get())) {
			throw new ErrorSaisieUser(this.getClass());
		}
	}

}
