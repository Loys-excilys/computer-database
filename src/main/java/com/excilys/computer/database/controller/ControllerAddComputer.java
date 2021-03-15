package com.excilys.computer.database.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.dto.CompanyDTO;
import com.excilys.computer.database.dto.ComputerFormAddDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperCompany;
import com.excilys.computer.database.mappeur.MapperComputer;
import com.excilys.computer.database.service.ServiceCompany;
import com.excilys.computer.database.service.ServiceComputer;

@Controller
public class ControllerAddComputer {

	@Autowired
	private ServiceCompany serviceCompany;

	@Autowired
	private ServiceComputer serviceComputer;

	@GetMapping("/AddComputer")
	public ModelAndView getAddComputerScreen(ComputerFormAddDTO computerFormAddDTO) {

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("AddComputer");
		Map<String, String> computerDTOs = new HashMap<String, String>();
		List<CompanyDTO> list = MapperCompany.listCompanyToListCompanyDTO(this.serviceCompany.getListCompany());
		for (CompanyDTO company : list) {
			computerDTOs.put(String.valueOf(company.getId()), company.getName());
		}
		modelView.addObject("listCompany", computerDTOs);
		modelView.getModel().put("ComputerFormAddDTO", computerFormAddDTO);
		return modelView;
	}

	@PostMapping("/AddComputer")
	public ModelAndView addComputer(ComputerFormAddDTO computerFormAddDTO) {
		List<CompanyDTO> listCompany = MapperCompany.listCompanyToListCompanyDTO(this.serviceCompany.getListCompany());
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("AddComputer");
		try {
			Computer computer = MapperComputer.computerFormAddDTOToComputer(computerFormAddDTO, listCompany);
			this.serviceComputer.addComputer(computer);
		} catch (ErrorSaisieUser errorUser) {
			errorUser.formatEntry();
			return this.getAddComputerScreen(computerFormAddDTO);

		}
		return modelView;
	}
}
