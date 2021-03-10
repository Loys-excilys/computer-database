package com.excilys.computer.database.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.dto.CompanyDTO;
import com.excilys.computer.database.dto.ComputerFormAddDTO;
import com.excilys.computer.database.error.ErreurIO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperCompany;
import com.excilys.computer.database.mappeur.MapperComputer;
import com.excilys.computer.database.service.Service;

/**
 * Servlet implementation class ServletAddComputer
 */
@WebServlet("/ServletAddComputer")
public class ServletAddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private Service service;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			session.setAttribute("listCompany", MapperCompany.listCompanyToListCompanyDTO(this.service.getServiceCompany().getListCompany()));
			this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/AddComputer.jsp").forward(request, response);
		} catch (ServletException errorServlet) {
			new ErreurIO(this.getClass()).redirectionFail(errorServlet);
		} catch (IOException errorIO) {
			new ErreurIO(this.getClass()).redirectionFail(errorIO);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		ComputerFormAddDTO computerFormAddDTO = MapperComputer.requestToComputerFormAddDTO(request);
		HttpSession session = request.getSession();
		String pathRedirection = "/index";
		List<CompanyDTO> listCompany = (List) session.getAttribute("listCompany");
		try {
			Computer computer = MapperComputer.computerFormAddDTOToComputer(computerFormAddDTO, listCompany);
			this.service.getServiceComputer().addComputer(computer);
			pathRedirection = "ServletComputer";
		} catch (ErrorSaisieUser errorUser) {
			pathRedirection = "ServletAddComputer";
			errorUser.formatEntry();
			session.setAttribute("currentEntry", computerFormAddDTO);
			session.setAttribute("errorSaisie", "Name ou date non valide, vérifiez vos informations");
		}
		try {
			response.sendRedirect(pathRedirection);
		} catch (IOException errorIO) {
			new ErreurIO(this.getClass()).redirectionFail(errorIO);
		}
	}
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		super.init(config);
	}

}
