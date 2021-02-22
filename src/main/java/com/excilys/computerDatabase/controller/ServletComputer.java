package com.excilys.computerDatabase.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.error.ErrorDAOCompany;
import com.excilys.computerDatabase.error.ErrorDAOComputer;
import com.excilys.computerDatabase.service.ServiceCompany;
import com.excilys.computerDatabase.service.ServiceComputer;
import com.excilys.computerDatabase.view.Page;

/**
 * Servlet implementation class ServletComputer
 */
public class ServletComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ServiceComputer serviceComputer = new ServiceComputer();
	private ServiceCompany serviceCompany = new ServiceCompany();
	private Page page = new Page();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		try {
			session.setAttribute("listComputer", this.serviceComputer.getListComputer(this.page.getPage()));
			this.getServletContext().getRequestDispatcher("/JSP/Computer.jsp").forward(request, response);
		} catch (ErrorDAOComputer errorListComputer) {
			errorListComputer.connectionLost();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String UserChoiceAction = (String) request.getParameter("UserChoiceAction");
		HttpSession session = request.getSession();
		String pathRedirection = "/index";
		switch(UserChoiceAction) {
			case "nextPage" :this.page.next();doGet(request, response);break;
			case "previousPage" :this.page.previous();doGet(request, response);break;
			case "addComputer":
				try {
					session.setAttribute("listCompany", this.serviceCompany.getListCompany());
					pathRedirection = "/JSP/AddComputer.jsp";
					
				} catch (ErrorDAOCompany e) {
					e.connectionLost();
				}
				break;
			case "Valider le form" : 
				List<Company> listCompany = (List)session.getAttribute("listCompany");
				Computer computer = new Computer(request.getParameter("computerName"),
						LocalDate.parse(request.getParameter("dateIntroduced")),
						LocalDate.parse(request.getParameter("dateDiscontinued")),
						listCompany.get(Integer.parseInt(request.getParameter("companyName"))-1));
				try {
					this.serviceComputer.addComputer(computer);
				} catch (ErrorDAOComputer e) {
					e.connectionLost();
				}
				pathRedirection = "/JSP/Computer";
				break;
				
		}
		try {
			this.getServletContext().getRequestDispatcher(pathRedirection).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
