package com.excilys.computerDatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;

import com.excilys.computerDatabase.controller.ServletAddComputer;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public class TestServletAddComputer {
	
	
	@Test
	public void testDoGet() {
		ServletRunner servletRunner = new ServletRunner();
		servletRunner.registerServlet( "computer-database/ServletAddComputer", ServletAddComputer.class.getName() );
		ServletUnitClient testServlet = servletRunner.newClient() ;
		WebRequest request   = new GetMethodWebRequest( "http://localhost:8080/computer-database/ServletAddComputer" );
	    WebResponse response;
		try {
			response = testServlet.getResponse( request );
			HttpSession session = testServlet.getSession(true);
			assertNotNull(response, "No response received");
		    assertEquals("text/plain", response.getContentType() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDoPostAddComputer() {
		ServletRunner servletRunner = new ServletRunner();
		servletRunner.registerServlet( "computer-database/ServletAddComputer", ServletAddComputer.class.getName() );
		ServletUnitClient testServlet = servletRunner.newClient() ;
		WebRequest request   = new PostMethodWebRequest( "http://localhost:8080/computer-database/ServletAddComputer");
		request.setParameter("userChoiceAction", "Valider le form");

		request.setParameter("computerName", "testHttpUnit");
		request.setParameter("dateIntroduced", "2015-06-20");
		request.setParameter("dateDiscontinued", "");
		request.setParameter("companyName", "");
	    WebResponse response;
		try {
			response = testServlet.getResponse( request );
			HttpSession session = testServlet.getSession(true);
			assertNotNull(response, "No response received");
		    assertEquals("text/plain", response.getContentType() );
		    assertEquals(ArrayList.class, session.getAttribute("listCompany").getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
