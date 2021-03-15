package com.excilys.computer.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.dto.ComputerDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperComputer;
import com.excilys.computer.database.service.ServiceComputer;

@Controller
public class ControllerComputer {

	@Autowired
	private ServiceComputer serviceComputer;
	
	@Autowired
	private SessionComputer session;

	@GetMapping("/Computer")
	public ModelAndView getComputerListScreen(@RequestParam(required = false) String page,
			@RequestParam(required = false) String numberEntry, @RequestParam(required = false) String search,
			@RequestParam(required = false) String orderField, @RequestParam(required = false) String sort) {
		Page vPage = new Page();
		List<ComputerDTO> listComputer = null;
		this.pagination(vPage, page, numberEntry);
		try {
			listComputer = this.getListComputer(vPage, search, orderField, sort);
		} catch (ErrorSaisieUser exception) {
			exception.formatEntry();
		}
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("Computer");
		modelView.getModel().put("maxNumberPrint", vPage.getMaxPrint());
		modelView.getModel().put("currentPage", vPage.getPage() + 1);
		modelView.getModel().put("numberComputer", vPage.getMaxComputer());
		modelView.getModel().put("listComputer", listComputer);
		return modelView;
	}
	
	private void pagination(Page page, String numPage, String numberEntry) {
		if (numPage != null) {
			page.setPage(Integer.parseInt(numPage) - 1);
		}
		if (numberEntry != null) {
			page.setMaxPrint(Integer.parseInt(numberEntry));
		}
	}

	private List<ComputerDTO> getListComputer(Page page, String search, String orderField, String sort)
			throws ErrorSaisieUser {
		if (search != null) {
			session.setSearch(search);
		}
		if (orderField != null && sort != null) {
			session.setOrderField(orderField);
			session.setSort(sort);
		}
		if (session.getSearch() != null && session.getOrderField() != null
				&& session.getSort() != null) {
			page.setMaxComputer(
					this.serviceComputer.getSearchNumberComputer(session.getSearch()));
			return MapperComputer.listComputerToListComputerDTO(
					this.serviceComputer.getResearchComputerOrder(session.getSearch(),
							session.getOrderField(), session.getSort(), page));
		} else if (session.getSearch() != null) {
			page.setMaxComputer(
					this.serviceComputer.getSearchNumberComputer(session.getSearch()));
			return MapperComputer.listComputerToListComputerDTO(
					this.serviceComputer.getSearchComputer(session.getSearch(), page));
		} else if (session.getOrderField() != null && session.getSort() != null) {
			page.setMaxComputer(this.serviceComputer.getNumberComputer());
			return MapperComputer.listComputerToListComputerDTO(this.serviceComputer.getListComputerOrder(
					session.getOrderField(), session.getSort(), page));
		} else {
			page.setMaxComputer(this.serviceComputer.getNumberComputer());
			return MapperComputer
					.listComputerToListComputerDTO(this.serviceComputer.getListComputer(page));

		}
	}
	@PostMapping("/Computer")
	public ModelAndView deleteComputer(@RequestParam String selection) {
		for (String id : selection.split(",")) {
			try {
				this.serviceComputer.deleteComputerById(Integer.parseInt(id));
			} catch (NumberFormatException exceptionUser) {
				new ErrorSaisieUser(this.getClass()).formatEntry();
			}
		}
		
		return this.getComputerListScreen(null, null, null, null, null);
	}
}
