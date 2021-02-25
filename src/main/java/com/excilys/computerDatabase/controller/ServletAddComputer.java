package com.excilys.computerDatabase.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.data.ComputerFactory;
import com.excilys.computerDatabase.error.ErrorDAOCompany;
import com.excilys.computerDatabase.error.ErrorDAOComputer;
import com.excilys.computerDatabase.error.ErrorSaisieUser;
import com.excilys.computerDatabase.service.Service;

/**
 * Servlet implementation class ServletAddComputer
 */
@WebServlet("/ServletAddComputer")
public class ServletAddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Service service = new Service();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAddComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			session.setAttribute("listCompany", this.service.getServiceCompany().getListCompany());
			this.getServletContext().getRequestDispatcher("/JSP/AddComputer.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ErrorDAOCompany e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pathRedirection = "/index";
		List<Company> listCompany = (List) session.getAttribute("listCompany");
		try {
			Computer computer = new ComputerFactory().getComputer(request.getParameter("computerName"),
					request.getParameter("dateIntroduced").compareTo("") != 0 ? LocalDate.parse(request.getParameter("dateIntroduced")) : null,
					request.getParameter("dateDiscontinued").compareTo("") != 0 ? LocalDate.parse(request.getParameter("dateDiscontinued")) : null,
					request.getParameter("companyName").compareTo("") != 0 ? listCompany.get(Integer.parseInt(request.getParameter("companyName"))-1) : null);
			this.service.getServiceComputer().addComputer(computer);
			pathRedirection = "/computer-database/ServletComputer";
		} catch (ErrorSaisieUser errorUser) {
			pathRedirection = "/computer-database/ServletAddComputer";
			errorUser.formatEntry();
		} catch (ErrorDAOComputer errorDAO) {
			errorDAO.connectionLost();	
		}
		
		
		try {
			response.sendRedirect(pathRedirection);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
