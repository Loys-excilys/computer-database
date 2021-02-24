package com.excilys.computerDatabase.data;

import com.excilys.computerDatabase.error.ErrorSaisieUser;

public class ValidateurComputer {
	
	public Computer getValidate(Computer computer) throws ErrorSaisieUser {
		if(computer.getName().compareTo("") == 0 || computer.getName() == null) {
			throw new ErrorSaisieUser();
		}
		return computer;
	}
}
