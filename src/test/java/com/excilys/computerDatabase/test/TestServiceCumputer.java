package com.excilys.computerDatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.data.ComputerFactory;
import com.excilys.computerDatabase.error.ErrorSaisieUser;
import com.excilys.computerDatabase.service.Service;


public class TestServiceCumputer{
	
	int idTest = 577;
	@Test
	public void testGetListComputer() throws Exception, ErrorSaisieUser {
		Service service;
		service = new Service();
		service.createService();
		
		assertEquals(ArrayList.class, service.getServiceComputer().getListComputer(0).getClass());
	}
	
	@Test
	public void testInsertComputer()throws Exception, ErrorSaisieUser{
		Service service;
		service = new Service();
		service.createService();
		Computer computer = new ComputerFactory().getComputer("testUnitaire",
				LocalDate.parse("2015-06-22"),
				LocalDate.parse("2021-04-30"),
				service.getServiceCompany().getCompany("ASUS"));
		
		
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
