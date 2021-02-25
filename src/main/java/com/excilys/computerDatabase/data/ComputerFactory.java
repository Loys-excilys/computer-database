package com.excilys.computerDatabase.data;

import java.time.LocalDate;

import com.excilys.computerDatabase.error.ErrorSaisieUser;

public class ComputerFactory {
	
	public Computer getComputer(int id, String name, LocalDate introduced, LocalDate discontinued, Company company) throws ErrorSaisieUser {
		return ValidateurComputer.getValidate(new Computer(id, name, introduced, discontinued, company));
	}
	
	public Computer getComputer(String name, LocalDate introduced, LocalDate discontinued, Company company) throws ErrorSaisieUser {
		return ValidateurComputer.getValidate(new Computer(0, name, introduced, discontinued, company));
	}
	
	public Computer getComputer(int id, String name, Object introduced, Object discontinued, Company company) throws ErrorSaisieUser {
		return ValidateurComputer.getValidate(new Computer(id, name, (LocalDate) introduced, (LocalDate) discontinued, company));
	}

	public Computer getComputer(String name, String introduced, String discontinued, Company company) throws ErrorSaisieUser {
		return ValidateurComputer.getValidate(
				new Computer(0, name,
						introduced.compareTo("") != 0 ? LocalDate.parse(introduced) : null,
						discontinued.compareTo("") != 0 ? LocalDate.parse(discontinued) : null,
						company));
	}
}
