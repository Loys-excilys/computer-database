package com.excilys.computerDatabase.test;

import junit.framework.*;

import java.time.LocalDate;
import java.util.ArrayList;

import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.service.Service;


public class TestServiceCumputer extends TestCase{
	
	int idTest = 577;

	public void testGetListComputer() throws Exception {
		Service service;
		service = new Service();
		service.createService();
		
		assertEquals(ArrayList.class, service.getServiceComputer().getListComputer(0).getClass());
	}
	
	public void testInsertComputer()throws Exception{
		Service service;
		service = new Service();
		service.createService();
		Computer computer = new Computer();
		
		computer.setName("testUnitaire");
		computer.setIntroduced(LocalDate.parse("2015-06-22"));
		computer.setDiscontinued(LocalDate.parse("2021-04-30"));
		computer.setCompany(service.getServiceCompany().getCompany("ASUS"));
		
		
		idTest = (int) service.getServiceComputer().addComputer(computer);

		assertEquals(computer.getName(), service.getServiceComputer().getComputer(idTest).get().getName());
		
		service.getServiceComputer().deleteComputer(idTest);
		
	}

	public void testGetComputer() throws Exception {
		Service service;
		service = new Service();
		service.createService();
		assertEquals(Computer.class, service.getServiceComputer().getComputer(idTest).get().getClass());
	}

	public void testUpdateComputer() throws Exception {
		Service service;
		service = new Service();
		service.createService();
		Computer computer = service.getServiceComputer().getComputer(idTest).get();
		
		computer.setName("testUnitaire2");
		
		service.getServiceComputer().updateComputer(computer);
		assertEquals(computer.getName(), service.getServiceComputer().getComputer(idTest).get().getName());
	}	
}
