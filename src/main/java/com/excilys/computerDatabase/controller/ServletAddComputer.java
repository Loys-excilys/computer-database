package com.excilys.computerDatabase.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.computerDatabase.DTO.CompanyDTO;
import com.excilys.computerDatabase.DTO.ComputerFormAddDTO;
import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.error.ErreurIO;
import com.excilys.computerDatabase.error.ErrorDAOCompany;
import com.excilys.computerDatabase.error.ErrorDAOComputer;
import com.excilys.computerDatabase.error.ErrorSaisieUser;
import com.excilys.computerDatabase.mappeur.MapperCompany;
import com.excilys.computerDatabase.mappeur.MapperComputer;
import com.excilys.computerDatabase.service.Service;

/**
 * Servlet implementation class ServletAddComputer
 */
@WebServlet("/ServletAddComputer")
public class ServletAddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Service service = Service.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAddComputer() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			session.setAttribute("listCompany", MapperCompany.ListCompanyToListCompanyDTO(this.service.getServiceCompany().getListCompany()));
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		ComputerFormAddDTO computerFormAddDTO = null;
		HttpSession session = request.getSession();
		String pathRedirection = "/index";
		List<CompanyDTO> listCompany = (List) session.getAttribute("listCompany");
		try {
			computerFormAddDTO = MapperComputer.requestToComputerFormAddDTO(request);
			Computer computer = MapperComputer.ComputerFormAddDTOToComputer(computerFormAddDTO, listCompany);
			this.service.getServiceComputer().addComputer(computer);
			pathRedirection = "/computer-database/ServletComputer";
		} catch (ErrorSaisieUser errorUser) {
			pathRedirection = "/computer-database/ServletAddComputer";
			errorUser.formatEntry();
			session.setAttribute("currentEntry", computerFormAddDTO);
			session.setAttribute("errorSaisie", "Name ou date non valide, vérifiez vos informations");
		}
		try {
			response.sendRedirect(pathRedirection);
		} catch (IOException errorIO) {
			new ErreurIO(this.getClass()).redirectionFail(errorIO);;
		}
	}

}
