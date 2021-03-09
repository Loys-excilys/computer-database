package com.excilys.computerDatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.excilys.computerDatabase.builder.ComputerBuilder;
import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.data.Page;
import com.excilys.computerDatabase.error.ErrorSaisieUser;
import com.excilys.computerDatabase.service.Service;


class TestServiceCumputer{
	
	int idTest = 1;
	@Test
	void testGetListComputer() throws Exception, ErrorSaisieUser {
		Service service;
		service = new Service();
		Page page = new Page();
		
		page.setPage(0);
		assertEquals("MacBook Pro 15.4 inch", service.getServiceComputer().getListComputer(page).get(0).getName());
	}
	
	@Test
	void testInsertComputer()throws Exception, ErrorSaisieUser{
		Service service;
		service = new Service();
		Computer computer = new ComputerBuilder()
				.addName("testUnitaire")
				.addIntroduced(Optional.of(LocalDate.parse("2015-06-22")))
				.addDiscontinued(Optional.of(LocalDate.parse("2021-04-30")))
				.addCompany(Optional.of(service.getServiceCompany().getCompany("Apple Inc.")))
				.getComputer();
		
		
		idTest = (int) service.getServiceComputer().addComputer(computer);

		assertEquals(service.getServiceComputer().getNumberComputer(), idTest);

		
	}
	
	@Test
	void testGetComputer() throws Exception, ErrorSaisieUser {
		Service service;
		service = new Service();
		assertEquals(Computer.class, service.getServiceComputer().getComputer(idTest).get().getClass());
	}

	@Test
	void testUpdateComputer() throws Exception, ErrorSaisieUser {
		Service service;
		service = new Service();
		Computer computer = service.getServiceComputer().getComputer(idTest).get();
		
		computer.setName("testUnitaire2");
		
		service.getServiceComputer().updateComputer(computer);
		assertEquals(computer.getName(), service.getServiceComputer().getComputer(idTest).get().getName());
	}	
}
