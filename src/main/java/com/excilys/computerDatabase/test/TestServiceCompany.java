package com.excilys.computerDatabase.test;

import java.util.ArrayList;

import com.excilys.computerDatabase.service.Service;

import junit.framework.TestCase;

public class TestServiceCompany extends TestCase{
	
	public void testGetListComputer() throws Exception {
		Service service;
		service = new Service();
		service.createService();
		
		assertEquals(ArrayList.class, service.getServiceCompany().getListCompany(0).getClass());
	}
	
	public void testGetAllListComputer() throws Exception {
		Service service;
		service = new Service();
		service.createService();
		
		assertEquals(ArrayList.class, service.getServiceCompany().getListCompany().getClass());
	}
}
