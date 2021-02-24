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
import com.excilys.computerDatabase.service.Service;
import com.excilys.computerDatabase.service.ServiceCompany;
import com.excilys.computerDatabase.service.ServiceComputer;
import com.excilys.computerDatabase.view.Page;

/**
 * Servlet implementation class ServletComputer
 */
public class ServletComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Service service = new Service();
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
			this.page.setPage(Integer.parseInt(request.getParameter("page"))-1);
		}
		if(request.getParameter("numberEntry") != null) {
			this.setNumberPrintComputer(Integer.parseInt(request.getParameter("numberEntry")));
			this.page.setPage(0);
		}
		try {
			session.setAttribute("currentPage", this.page.getPage()+1);
			session.setAttribute("maxNumberPrint", this.getNumberPrintComputer());
			session.setAttribute("numberComputer", this.service.getServiceComputer().getNumberComputer());
			session.setAttribute("listComputer", this.service.getServiceComputer().getListComputer(this.page.getPage()));
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
		String UserChoiceAction = (String) request.getParameter("userChoiceAction");
		HttpSession session = request.getSession();
		String pathRedirection = "/index";
		switch(UserChoiceAction) {
			case "Add Computer":
				try {
					session.setAttribute("listCompany", this.service.getServiceCompany().getListCompany());
					pathRedirection = "/JSP/AddComputer.jsp";
					
				} catch (ErrorDAOCompany e) {
					e.connectionLost();
				}
				break;
			case "Valider le form" : 
				List<Company> listCompany = (List) session.getAttribute("listCompany");
				Computer computer = new Computer(request.getParameter("computerName"),
						request.getParameter("dateIntroduced").compareTo("") != 0 ? LocalDate.parse(request.getParameter("dateIntroduced")) : null,
						request.getParameter("dateDiscontinued").compareTo("") != 0 ? LocalDate.parse(request.getParameter("dateDiscontinued")) : null,
						request.getParameter("companyName").compareTo("") != 0 ? listCompany.get(Integer.parseInt(request.getParameter("companyName"))-1) : null);

				try {
					this.service.getServiceComputer().addComputer(computer);
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
	
	private void setNumberPrintComputer(int number) {
		this.service.getServiceComputer().setNumberPrint(number);
	}
	
	private int getNumberPrintComputer() {
		return this.service.getServiceComputer().getNumberPrint();
	}
}
