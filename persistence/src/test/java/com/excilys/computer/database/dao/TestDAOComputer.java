package com.excilys.computer.database.dao;

import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.junit.Test;

import com.excilys.computer.database.config.PersistenceConfigContext;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfigContext.class }, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = { "getDataSource" })
public class TestDAOComputer {

	@Autowired
	DAOComputer DAOComputer;

	@Test
	@DatabaseSetup("classpath:data.xml")
	public void testFind() {
		try {
			assertEquals("computer_1", DAOComputer.getComputer(1).get().getName());
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DatabaseSetup("classpath:data.xml")
	public void testCounter() {
		assertEquals(4, DAOComputer.getNumberComputer());
	}
	
	@Test
	@DatabaseSetup("classpath:data.xml")
	public void testGetListComputer() {
		assertEquals(4, DAOComputer.getListComputer(new Page()).size());
	}
	
	@Test
	@DatabaseSetup("classpath:data.xml")
	public void testGetListComputerOrder() {
		try {
			assertEquals("computer_4", DAOComputer.getListComputerOrder("introduced", "ASC", new Page()).get(0).getName());
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
	}
}
