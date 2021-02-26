package com.excilys.computerDatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.data.ComputerBuilder;
import com.excilys.computerDatabase.error.ErrorSaisieUser;
import com.excilys.computerDatabase.service.Service;
import com.excilys.computerDatabase.view.Page;


public class TestServiceCumputer{
	
	int idTest = 577;
	@Test
	public void testGetListComputer() throws Exception, ErrorSaisieUser {
		Service service;
		service = new Service();
		service.createService();
		Page page = new Page();
		
		
		assertEquals(ArrayList.class, service.getServiceComputer().getListComputer(page).getClass());
	}
	
	@Test
	public void testInsertComputer()throws Exception, ErrorSaisieUser{
		Service service;
		service = new Service();
		service.createService();
		Computer computer = new ComputerBuilder()
				.addName("testUnitaire")
				.addIntroduced(Optional.of(LocalDate.parse("2015-06-22")))
				.addDiscontinued(Optional.of(LocalDate.parse("2021-04-30")))
				.addCompany(Optional.of(service.getServiceCompany().getCompany("ASUS")))
				.getComputer();
		
		
		idTest = (int) service.getServiceComputer().addComputer(computer);

		assertEquals(computer.getName(), service.getServiceComputer().getComputer(idTest).get().getName());
		
		service.getServiceComputer().deleteComputer(idTest);
		
	}
	
	@Test
	public void testGetComputer() throws Exception, ErrorSaisieUser {
		Service service;
		service = new Service();
		service.createService();
		assertEquals(Computer.class, service.getServiceComputer().getComputer(idTest).get().getClass());
	}

	@Test
	public void testUpdateComputer() throws Exception, ErrorSaisieUser {
		Service service;
		service = new Service();
		service.createService();
		Computer computer = service.getServiceComputer().getComputer(idTest).get();
		
		computer.setName("testUnitaire2");
		
		service.getServiceComputer().updateComputer(computer);
		assertEquals(computer.getName(), service.getServiceComputer().getComputer(idTest).get().getName());
	}	
}
