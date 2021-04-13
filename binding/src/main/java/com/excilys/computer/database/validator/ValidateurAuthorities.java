package com.excilys.computer.database.validator;

import com.excilys.computer.database.data.Authorities;
import com.excilys.computer.database.error.ErrorSaisieUser;

public class ValidateurAuthorities {
	
	public Authorities valide(Authorities authorities) throws ErrorSaisieUser {
		if(authorities.getId() < 1 || authorities.getAuthority().trim().isBlank()) {
			throw new ErrorSaisieUser(this.getClass());
		}
		return authorities;
	}
}
