package com.excilys.computerDatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.excilys.computer.database.service.Service;

class TestServiceCompany {

	@Test
	void testGetListCompany() throws Exception {
		Service service;
		service = new Service();

		assertEquals(ArrayList.class, service.getServiceCompany().getListCompany(0).getClass());
	}

	@Test
	void testGetAllListCompany() throws Exception {
		Service service;
		service = new Service();

		assertEquals(ArrayList.class, service.getServiceCompany().getListCompany().getClass());
	}
}
