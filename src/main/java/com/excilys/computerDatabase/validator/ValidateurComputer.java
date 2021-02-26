package com.excilys.computerDatabase.validator;

import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.error.ErrorSaisieUser;

abstract public class ValidateurComputer {
	
	public static Computer getValidate(Computer computer) throws ErrorSaisieUser {
		valideName(computer);
		valideDate(computer);
		return computer;
	}
	
	public static void valideName(Computer computer) throws ErrorSaisieUser {
		if(computer.getName().compareTo("") == 0 || computer.getName() == null) {
			throw new ErrorSaisieUser();
		}
	}
	
	public static void valideDate(Computer computer) throws ErrorSaisieUser {
		if(computer.getIntroduced().isPresent() && computer.getDiscontinued().isPresent()) {
			if(!computer.getIntroduced().get().isBefore(computer.getDiscontinued().get())) {
				throw new ErrorSaisieUser();
			}
		}
	}

}
