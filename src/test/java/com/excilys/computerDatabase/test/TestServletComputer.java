
package com.excilys.computerDatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;

import com.excilys.computer.database.controller.ServletComputer;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

class TestServletComputer {

	@Test
	void testDoGetPageUndefined() {
		ServletRunner servletRunner = new ServletRunner();
		servletRunner.registerServlet( "computer-database/ServletComputer", ServletComputer.class.getName() );
		ServletUnitClient testServlet = servletRunner.newClient() ;
		WebRequest request   = new GetMethodWebRequest( "http://localhost:8080/computer-database/ServletComputer" );
	    WebResponse response;
		try {
			response = testServlet.getResponse( request );
			HttpSession session = testServlet.getSession(true);
			assertNotNull(response, "No response received");
		    assertEquals("text/plain", response.getContentType() );
		    assertEquals(ArrayList.class, session.getAttribute("listComputer").getClass());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDoGetPagedefined() {
		ServletRunner servletRunner = new ServletRunner();
		servletRunner.registerServlet( "computer-database/ServletComputer", ServletComputer.class.getName() );
		ServletUnitClient testServlet = servletRunner.newClient() ;
		WebRequest request   = new GetMethodWebRequest( "http://localhost:8080/computer-database/ServletComputer" );
		request.setParameter("page", "2");
	    WebResponse response;
		try {
			response = testServlet.getResponse( request );
			HttpSession session = testServlet.getSession(true);
			assertNotNull(response, "No response received");
		    assertEquals("text/plain", response.getContentType() );
		    assertEquals(ArrayList.class, session.getAttribute("listComputer").getClass());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}