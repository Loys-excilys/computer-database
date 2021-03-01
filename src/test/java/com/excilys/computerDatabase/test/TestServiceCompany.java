package com.excilys.computerDatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.excilys.computerDatabase.service.Service;


public class TestServiceCompany{
	
	@Test
	public void testGetListCompany() throws Exception {
		Service service;
		service = Service.getInstance();
		service.createService();
		
		assertEquals(ArrayList.class, service.getServiceCompany().getListCompany(0).getClass());
	}
	
	@Test
	public void testGetAllListCompany() throws Exception {
		Service service;
		service = Service.getInstance();
		service.createService();
		
		assertEquals(ArrayList.class, service.getServiceCompany().getListCompany().getClass());
	}
}
