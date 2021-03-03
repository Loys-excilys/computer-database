package com.excilys.computerDatabase.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.computerDatabase.DTO.ComputerDTO;
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
	
	private Service service = Service.getInstance();       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletComputer() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		Page page = new Page();
		List<ComputerDTO> listComputer = null;
		HttpSession session = request.getSession();
		if(request.getParameter("page") != null) {
			page.setPage(Integer.parseInt(request.getParameter("page"))-1);
		}
		if(request.getParameter("numberEntry") != null) {
			page.setMaxPrint(Integer.parseInt(request.getParameter("numberEntry")));;
			page.setPage(0);
		}
		if(request.getParameter("search") != null){
			try {
				listComputer = MapperComputer.ListComputerToListComputerDTO(
						this.service.getServiceComputer().getResearchComputer(request.getParameter("search"), page));
			} catch (ErrorDAOComputer e) {
				e.printStackTrace();
			} catch (ErrorSaisieUser e) {
				e.printStackTrace();
			}
		}else if(request.getParameter("orderField") != null && request.getParameter("sort") != null) {
			try {
				listComputer = MapperComputer.ListComputerToListComputerDTO(
						this.service.getServiceComputer().getListComputerOrder(request.getParameter("orderField"),request.getParameter("sort"), page));
			} catch (ErrorDAOComputer | ErrorSaisieUser e) {
				e.printStackTrace();
			}
		}else {
			try {
				listComputer = MapperComputer.ListComputerToListComputerDTO(
						this.service.getServiceComputer().getListComputer(page));
			} catch (ErrorDAOComputer e) {
				e.printStackTrace();
			} catch (ErrorSaisieUser e) {
				e.printStackTrace();
			}
		}
		
		try {
			session.setAttribute("currentPage", page.getPage());
			session.setAttribute("maxNumberPrint", page.getMaxPrint());
			try {
				session.setAttribute("numberComputer", this.service.getServiceComputer().getNumberComputer());
			} catch (ErrorDAOComputer e) {
				e.printStackTrace();
			}
			session.setAttribute("listComputer", listComputer);
			
			this.getServletContext().getRequestDispatcher("/JSP/Computer.jsp").forward(request, response);
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
		final String SEPARATEUR = ",";
		
		String[] ids = request.getParameter("selection").split(SEPARATEUR);
		for(String id : ids) {
			try {
				this.service.getServiceComputer().deleteComputerById(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ErrorDAOComputer e) {
				e.printStackTrace();
			}
		}
		try {
			response.sendRedirect("/computer-database/ServletComputer");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
