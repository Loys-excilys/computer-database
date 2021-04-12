package com.excilys.computer.database.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.dto.CompanyDTO;
import com.excilys.computer.database.dto.ComputerDTO;
import com.excilys.computer.database.dto.ComputerFormAddDTO;
import com.excilys.computer.database.dto.ComputerFormUpdateDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperCompany;
import com.excilys.computer.database.mappeur.MapperComputer;
import com.excilys.computer.database.service.ServiceCompany;
import com.excilys.computer.database.service.ServiceComputer;


public class ControllerComputer {

	@Autowired
	private ServiceComputer serviceComputer;
	@Autowired
	private ServiceCompany serviceCompany;
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
		modelView.getModel().put("numberComputer", vPage.getMaxComputer());
		modelView.getModel().put("listComputer", listComputer);
		modelView.addObject("session", session);
		return modelView;
	}

	private void pagination(Page page, String numPage, String numberEntry) {
		if (numPage != null) {
			session.setCurrentPage(Integer.parseInt(numPage));
		}
		if (numberEntry != null) {
			session.setMaxNumberPrint(Integer.parseInt(numberEntry));
		}
		if (session.getCurrentPage() == 0) {
			session.setCurrentPage(page.getPage() + 1);
		}

		if (session.getMaxNumberPrint() == 0) {
			session.setMaxNumberPrint(page.getMaxPrint());
		}
		page.setPage(session.getCurrentPage() - 1);
		page.setMaxPrint(session.getMaxNumberPrint());
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
		if (session.getSearch() != null && session.getOrderField() != null && session.getSort() != null) {
			page.setMaxComputer(this.serviceComputer.getSearchNumberComputer(session.getSearch()));
			return new MapperComputer().listComputerToListComputerDTO(this.serviceComputer
					.getResearchComputerOrder(session.getSearch(), session.getOrderField(), session.getSort(), page));
		} else if (session.getSearch() != null) {
			page.setMaxComputer(this.serviceComputer.getSearchNumberComputer(session.getSearch()));
			return new MapperComputer()
					.listComputerToListComputerDTO(this.serviceComputer.getSearchComputer(session.getSearch(), page));
		} else if (session.getOrderField() != null && session.getSort() != null) {
			page.setMaxComputer(this.serviceComputer.getNumberComputer());
			return new MapperComputer().listComputerToListComputerDTO(
					this.serviceComputer.getListComputerOrder(session.getOrderField(), session.getSort(), page));
		} else {
			page.setMaxComputer(this.serviceComputer.getNumberComputer());
			return new MapperComputer().listComputerToListComputerDTO(this.serviceComputer.getListComputer(page));

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
	
	@GetMapping("/AddComputer")
	public ModelAndView getAddComputerScreen(ComputerFormAddDTO computerFormAddDTO) {

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("AddComputer");
		Map<String, String> computerDTOs = new HashMap<String, String>();
		List<CompanyDTO> list = new MapperCompany().listCompanyToListCompanyDTO(this.serviceCompany.getListCompany());
		for (CompanyDTO company : list) {
			computerDTOs.put(String.valueOf(company.getId()), company.getName());
		}
		modelView.addObject("listCompany", computerDTOs);
		modelView.getModel().put("ComputerFormAddDTO", computerFormAddDTO);
		return modelView;
	}

	@PostMapping("/AddComputer")
	public ModelAndView addComputer(@ModelAttribute("ComputerFormAddDTO")ComputerFormAddDTO computerFormAddDTO) {
		List<CompanyDTO> listCompany = new MapperCompany().listCompanyToListCompanyDTO(this.serviceCompany.getListCompany());
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("AddComputer");
		try {
			Computer computer = new MapperComputer().computerFormAddDTOToComputer(computerFormAddDTO, listCompany);
			this.serviceComputer.addComputer(computer);
		} catch (ErrorSaisieUser errorUser) {
			errorUser.formatEntry();
			return this.getAddComputerScreen(computerFormAddDTO);

		}
		return modelView;
	}
	
	
	@GetMapping("/UpdateComputer")
	public ModelAndView getUpdateComputerScreen(ComputerFormUpdateDTO computerFormUpdateDTO,
			@RequestParam(required = false) String id) {
		System.out.println("\n\n" + id);
		if (id != null) {
			try {
				computerFormUpdateDTO = new MapperComputer()
						.computerToComputerFormUpdateDTO(this.serviceComputer.getComputer(Integer.parseInt(id)).get());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ErrorSaisieUser e) {
				e.printStackTrace();
			}
		}
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("UpdateComputer");
		Map<String, String> computerDTOs = new HashMap<String, String>();
		List<CompanyDTO> list = new MapperCompany().listCompanyToListCompanyDTO(this.serviceCompany.getListCompany());
		for (CompanyDTO company : list) {
			computerDTOs.put(String.valueOf(company.getId()), company.getName());
		}
		modelView.addObject("listCompany", computerDTOs);
		modelView.getModel().put("ComputerFormUpdateDTO", computerFormUpdateDTO);
		return modelView;
	}

	@PostMapping("/UpdateComputer")
	public ModelAndView updateComputer(ComputerFormUpdateDTO computerFormAddDTO) {
		List<CompanyDTO> listCompany = new MapperCompany().listCompanyToListCompanyDTO(this.serviceCompany.getListCompany());
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("UpdateComputer");
		try {
			Computer computer = new MapperComputer().computerFormUpdateDTOToComputer(computerFormAddDTO, listCompany);
			this.serviceComputer.addComputer(computer);
		} catch (ErrorSaisieUser errorUser) {
			errorUser.formatEntry();
			return this.getUpdateComputerScreen(computerFormAddDTO, null);

		}
		return modelView;
	}
}
