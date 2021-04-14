package com.excilys.computer.database.validator;

import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.error.ErrorSaisieUser;

public class ValidateurCompany {

	public Company Valide(Company company) throws ErrorSaisieUser {
		if(company.getName().trim().isBlank()) {
			throw new ErrorSaisieUser(this.getClass());
		}
		return company;
	}
}
