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
import com.excilys.computerDatabase.DTO.ComputerFormUpdateDTO;
import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.error.ErreurIO;
import com.excilys.computerDatabase.error.ErrorDAOCompany;
import com.excilys.computerDatabase.error.ErrorDAOComputer;
import com.excilys.computerDatabase.error.ErrorSaisieUser;
import com.excilys.computerDatabase.mappeur.MapperCompany;
import com.excilys.computerDatabase.mappeur.MapperComputer;
import com.excilys.computerDatabase.service.Service;

/**
 * Servlet implementation class ServletUpdateComputerSer
 */
@WebServlet("/ServletUpdateComputer")
public class ServletUpdateComputer extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	private Service service = Service.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUpdateComputer() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			session.setAttribute("listCompany", MapperCompany.ListCompanyToListCompanyDTO(this.service.getServiceCompany().getListCompany()));
			session.setAttribute("updateComputer", MapperComputer.computerToComputerDTO(this.service.getServiceComputer()
					.getComputer(Integer.parseInt(request.getParameter("id"))).get()));
			this.getServletContext().getRequestDispatcher("/JSP/UpdateComputer.jsp").forward(request, response);
		} catch (ServletException errorServlet) {
			new ErreurIO(this.getClass());
		} catch (IOException errorIO) {
			new ErreurIO(this.getClass());
		} catch (ErrorDAOCompany errorDAO) {
			errorDAO.connectionLost();
		} catch (ErrorDAOComputer e) {
			e.printStackTrace();
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		ComputerFormUpdateDTO computerFormUpdateDTO = null;
		HttpSession session = request.getSession();
		String pathRedirection = "/index";
		List<CompanyDTO> listCompany = (List) session.getAttribute("listCompany");
		try {
			computerFormUpdateDTO = MapperComputer.requestToComputerFormUpdateDTO(request);
			Computer computer = MapperComputer.ComputerFormUpdateDTOToComputer(computerFormUpdateDTO, listCompany);
			this.service.getServiceComputer().updateComputer(computer);
			pathRedirection = "/computer-database/ServletComputer";
		} catch (ErrorSaisieUser errorUser) {
			pathRedirection = "/computer-database/ServletAddComputer";
			errorUser.formatEntry();
			session.setAttribute("updateComputer", computerFormUpdateDTO);
			session.setAttribute("errorSaisie", "Name ou date non valide, vérifiez vos informations");
		} catch (ErrorDAOComputer errorDAO) {
			errorDAO.connectionLost();	
		}
		
		
		try {
			response.sendRedirect(pathRedirection);
		} catch (IOException errorIO) {
			new ErreurIO(this.getClass());
		}
	}
}
