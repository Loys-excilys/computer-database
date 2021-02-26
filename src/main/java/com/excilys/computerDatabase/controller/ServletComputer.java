package com.excilys.computerDatabase.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.computerDatabase.data.Page;
import com.excilys.computerDatabase.error.ErreurIO;
import com.excilys.computerDatabase.error.ErrorDAOComputer;
import com.excilys.computerDatabase.error.ErrorSaisieUser;
import com.excilys.computerDatabase.mappeur.MapperComputer;
import com.excilys.computerDatabase.service.Service;

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
			this.page.setMaxPrint(Integer.parseInt(request.getParameter("numberEntry")));;
			this.page.setPage(0);
		}
		try {
			session.setAttribute("currentPage", this.page.getPage()+1);
			session.setAttribute("maxNumberPrint", this.page.getMaxPrint());
			session.setAttribute("numberComputer", this.service.getServiceComputer().getNumberComputer());
			try {
				session.setAttribute("listComputer", MapperComputer.ListComputerToListComputerDTO(
						this.service.getServiceComputer().getListComputer(this.page)));
			} catch (ErrorSaisieUser errorUser) {
				errorUser.formatEntry();
			}
			this.getServletContext().getRequestDispatcher("/JSP/Computer.jsp").forward(request, response);
		} catch (ErrorDAOComputer errorListComputer) {
			errorListComputer.connectionLost();
		} catch (ServletException errorServlet) {
			new ErreurIO(this.getClass());
		} catch (IOException errorIO) {
			new ErreurIO(this.getClass());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
	}
}
