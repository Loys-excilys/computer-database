package com.excilys.computer.database.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.excilys.computer.database.builder.BuilderComputer;
import com.excilys.computer.database.config.ServiceConfigContext;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.service.ServiceCompany;
import com.excilys.computer.database.service.ServiceComputer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfigContext.class })
@WebAppConfiguration
class TestServiceCumputer {

	
	@Autowired
	private ServiceComputer serviceComputer;
	@Autowired
	private ServiceCompany serviceCompany;
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}
	int idTest = 582;

	@Test
	void testGetListComputer() throws Exception, ErrorSaisieUser {
		Page page = new Page();

		page.setPage(0);
		assertEquals(Computer.class, serviceComputer.getListComputer(page).get(0).getClass());
	}

	@Test
	void testInsertComputer() throws Exception, ErrorSaisieUser {
		Computer computer = new BuilderComputer().addName("testUnitaire")
				.addIntroduced(LocalDate.parse("2015-06-22"))
				.addDiscontinued(LocalDate.parse("2021-04-30"))
				.addCompany(serviceCompany.getCompany("Apple Inc.")).build();

		serviceComputer.addComputer(computer);

		assertEquals(serviceComputer.getListComputerOrder("id", "DESC", new Page()).get(0).getName(), computer.getName());

	}

	@Test
	void testGetComputer() throws Exception, ErrorSaisieUser {
		assertEquals(Computer.class, serviceComputer.getComputer(idTest).get().getClass());
	}

	@Test
	void testUpdateComputer() throws Exception, ErrorSaisieUser {
		Computer computer = serviceComputer.getComputer(idTest).get();

		computer.setName("testUnitaire2");

		serviceComputer.updateComputer(computer);
		assertEquals(computer.getName(), serviceComputer.getComputer(idTest).get().getName());
	}
}
