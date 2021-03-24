package com.excilys.computer.database.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

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

import com.excilys.computer.database.service.config.ServiceConfigContext;
import com.excilys.computer.database.service.ServiceCompany;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfigContext.class })
@WebAppConfiguration
public class TestServiceCompany {

	@Autowired
	private ServiceCompany serviceCompany;
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
//	@BeforeEach
//	public void setup() throws Exception {
//	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//	}
//	
//	@Test
//	public void testGetListCompany() throws Exception {
//
//		assertEquals(ArrayList.class, serviceCompany.getListCompany(0).getClass());
//	}
//
//	@Test
//	public void testGetAllListCompany() throws Exception {
//
//		assertEquals(ArrayList.class, serviceCompany.getListCompany().getClass());
//	}
}
