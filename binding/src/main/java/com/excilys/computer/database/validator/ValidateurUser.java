package com.excilys.computer.database.validator;

import com.excilys.computer.database.data.User;
import com.excilys.computer.database.error.ErrorSaisieUser;

public class ValidateurUser {

	public User valide(User user) throws ErrorSaisieUser {
		if (user.getUsername() == null || user.getUsername().isBlank()
				|| user.getPassword() == null || user.getPassword().isBlank()
				|| user.getAuthority() == null
				) {
			throw new ErrorSaisieUser(this.getClass());
		}
		
		return user;
	}
	
	public User valideStream(User user) throws ErrorSaisieUser {
		if (user.getUsername() == null || user.getUsername().isBlank()
				|| user.getAuthority() == null
				) {
			throw new ErrorSaisieUser(this.getClass());
		}
		
		return user;
	}
	
}
