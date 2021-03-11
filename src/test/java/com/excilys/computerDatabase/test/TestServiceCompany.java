package com.excilys.computerDatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.excilys.computer.database.service.ServiceCompany;

class TestServiceCompany {

	@Test
	void testGetListCompany() throws Exception {
		ServiceCompany serviceCompany = new ServiceCompany();

		assertEquals(ArrayList.class, serviceCompany.getListCompany(0).getClass());
	}

	@Test
	void testGetAllListCompany() throws Exception {
		ServiceCompany serviceCompany = new ServiceCompany();

		assertEquals(ArrayList.class, serviceCompany.getListCompany().getClass());
	}
}
