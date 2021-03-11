package com.excilys.computerDatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.excilys.computer.database.builder.ComputerBuilder;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.service.ServiceCompany;
import com.excilys.computer.database.service.ServiceComputer;

class TestServiceCumputer {

	int idTest = 1;

	@Test
	void testGetListComputer() throws Exception, ErrorSaisieUser {
		ServiceComputer serviceComputer = new ServiceComputer();
		Page page = new Page();

		page.setPage(0);
		assertEquals("MacBook Pro 15.4 inch", serviceComputer.getListComputer(page).get(0).getName());
	}

	@Test
	void testInsertComputer() throws Exception, ErrorSaisieUser {
		ServiceComputer serviceComputer = new ServiceComputer();
		ServiceCompany serviceCompany = new ServiceCompany();
		Computer computer = new ComputerBuilder().addName("testUnitaire")
				.addIntroduced(Optional.of(LocalDate.parse("2015-06-22")))
				.addDiscontinued(Optional.of(LocalDate.parse("2021-04-30")))
				.addCompany(serviceCompany.getCompany("Apple Inc.")).getComputer();

		idTest = (int) serviceComputer.addComputer(computer);

		assertEquals(serviceComputer.getNumberComputer(), idTest);

	}

	@Test
	void testGetComputer() throws Exception, ErrorSaisieUser {
		ServiceComputer serviceComputer = new ServiceComputer();
		assertEquals(Computer.class, serviceComputer.getComputer(idTest).get().getClass());
	}

	@Test
	void testUpdateComputer() throws Exception, ErrorSaisieUser {
		ServiceComputer serviceComputer = new ServiceComputer();
		Computer computer = serviceComputer.getComputer(idTest).get();

		computer.setName("testUnitaire2");

		serviceComputer.updateComputer(computer);
		assertEquals(computer.getName(), serviceComputer.getComputer(idTest).get().getName());
	}
}
