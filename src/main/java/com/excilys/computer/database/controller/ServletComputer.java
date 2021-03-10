package com.excilys.computer.database.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.dto.ComputerDTO;
import com.excilys.computer.database.error.ErreurIO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperComputer;
import com.excilys.computer.database.service.Service;

/**
 * Servlet implementation class ServletComputer
 */
@Controller
public class ServletComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAX_NUMBER_ENTRY = "maxNumberPrint";
	private static final String SEARCH = "search";
	private static final String ORDER_FIELD = "orderField";
	private static final String SORT = "sort";

	@Autowired
	private Service service;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		Page page = new Page();
		List<ComputerDTO> listComputer = null;
		HttpSession session = request.getSession();
		this.pagination(request, page, session);
		try {
			listComputer = this.getListComputer(request, session, page);
		} catch (ErrorSaisieUser exception) {
			exception.formatEntry();
		}
		try {
			session.setAttribute("currentPage", (page.getPage() + 1));
			session.setAttribute("numberComputer", page.getMaxComputer());
			session.setAttribute("listComputer", listComputer);
			this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/Computer.jsp").forward(request, response);
		} catch (ServletException errorServlet) {
			new ErreurIO(this.getClass()).redirectionFail(errorServlet);
		} catch (IOException errorIO) {
			new ErreurIO(this.getClass()).redirectionFail(errorIO);
		}
	}

	private void pagination(HttpServletRequest request, Page page, HttpSession session) {
		if (request.getParameter("page") != null) {
			page.setPage(Integer.parseInt(request.getParameter("page")) - 1);
		}
		if (request.getParameter("numberEntry") != null) {
			session.setAttribute(MAX_NUMBER_ENTRY, request.getParameter("numberEntry"));
		}
		if (session.getAttribute(MAX_NUMBER_ENTRY) != null) {
			page.setMaxPrint(Integer.parseInt(session.getAttribute(MAX_NUMBER_ENTRY).toString()));
		} else {
			session.setAttribute(MAX_NUMBER_ENTRY, page.getMaxPrint());
		}
	}

	private List<ComputerDTO> getListComputer(HttpServletRequest request, HttpSession session, Page page)
			throws ErrorSaisieUser {
		if (request.getParameter(SEARCH) != null) {
			session.setAttribute(SEARCH, request.getParameter(SEARCH));
		}
		if (request.getParameter(ORDER_FIELD) != null && request.getParameter(SORT) != null) {
			session.setAttribute(ORDER_FIELD, request.getParameter(ORDER_FIELD));
			session.setAttribute(SORT, request.getParameter(SORT));
		}
		if (session.getAttribute(SEARCH) != null && session.getAttribute(ORDER_FIELD) != null
				&& session.getAttribute(SORT) != null) {
			page.setMaxComputer(
					this.service.getServiceComputer().getSearchNumberComputer(session.getAttribute(SEARCH).toString()));

			return MapperComputer.listComputerToListComputerDTO(
					this.service.getServiceComputer().getResearchComputerOrder(session.getAttribute(SEARCH).toString(),
							session.getAttribute(ORDER_FIELD).toString(), session.getAttribute(SORT).toString(), page));
		} else if (session.getAttribute(SEARCH) != null) {
			page.setMaxComputer(
					this.service.getServiceComputer().getSearchNumberComputer(session.getAttribute(SEARCH).toString()));
			return MapperComputer.listComputerToListComputerDTO(
					this.service.getServiceComputer().getSearchComputer(session.getAttribute(SEARCH).toString(), page));
		} else if (session.getAttribute(ORDER_FIELD) != null && session.getAttribute(SORT) != null) {
			page.setMaxComputer(this.service.getServiceComputer().getNumberComputer());
			return MapperComputer.listComputerToListComputerDTO(this.service.getServiceComputer().getListComputerOrder(
					session.getAttribute(ORDER_FIELD).toString(), session.getAttribute(SORT).toString(), page));
		} else {
			page.setMaxComputer(this.service.getServiceComputer().getNumberComputer());
			return MapperComputer
					.listComputerToListComputerDTO(this.service.getServiceComputer().getListComputer(page));

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameter("selection").split(",");
		for (String id : ids) {
			try {
				this.service.getServiceComputer().deleteComputerById(Integer.parseInt(id));
			} catch (NumberFormatException exceptionUser) {
				new ErrorSaisieUser(this.getClass()).formatEntry();
			}
		}
		try {
			response.sendRedirect("ServletComputer");
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
