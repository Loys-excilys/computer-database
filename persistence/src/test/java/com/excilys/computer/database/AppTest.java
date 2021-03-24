package com.excilys.computer.database;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.junit.jupiter.api.Test;

import com.excilys.computer.database.dao.DAOComputer;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.persistence.config.PersistenceConfigContext;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * Unit test for simple App.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfigContext.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class AppTest {

	@Autowired
	DAOComputer DAOComputer;

	@Test
	@DatabaseSetup("/data.xml")
	public void testFind() throws Exception {
		try {
			assertEquals("Computer_1", DAOComputer.getComputer(1).get().getName());
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
	}
}
