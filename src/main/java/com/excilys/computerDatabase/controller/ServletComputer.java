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
		if(request.getParameter("page") != null) {
			this.page.setPage(Integer.parseInt(request.getParameter("page")));
		}
		
		try {
			session.setAttribute("currentPage", this.page.getPage());
			session.setAttribute("numberComputer", this.serviceComputer.getNumberComputer());
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
		String userChoiceAction = (String) request.getParameter("userChoiceAction");
		HttpSession session = request.getSession();
		String pathRedirection = "/index";

		switch(userChoiceAction) {
			case "Add Computer":
				try {
					session.setAttribute("listCompany", this.serviceCompany.getListCompany());
					pathRedirection = "/JSP/AddComputer.jsp";
					
				} catch (ErrorDAOCompany e) {
					e.connectionLost();
				}
				break;
			case "Valider le form" :
				if(request.getParameter("computerName").compareTo("") != 0) {
					List<Company> listCompany = (List) session.getAttribute("listCompany");
					
					Computer computer = new Computer(request.getParameter("computerName"),
							request.getParameter("dateIntroduced").compareTo("") != 0 ? LocalDate.parse(request.getParameter("dateIntroduced")) : null,
							request.getParameter("dateDiscontinued").compareTo("") != 0 ? LocalDate.parse(request.getParameter("dateDiscontinued")) : null,
							request.getParameter("companyName").compareTo("") != 0 ? listCompany.get(Integer.parseInt(request.getParameter("companyName"))-1) : null);
					try {
						this.serviceComputer.addComputer(computer);
					} catch (ErrorDAOComputer e) {
						e.connectionLost();
					}
					pathRedirection = "/JSP/Computer";
				}else {
					pathRedirection = "/JSP/AddComputer.jsp";				
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
}
