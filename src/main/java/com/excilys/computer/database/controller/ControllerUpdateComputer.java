package com.excilys.computer.database.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.dto.CompanyDTO;
import com.excilys.computer.database.dto.ComputerFormUpdateDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperCompany;
import com.excilys.computer.database.mappeur.MapperComputer;
import com.excilys.computer.database.service.ServiceCompany;
import com.excilys.computer.database.service.ServiceComputer;

@Controller
public class ControllerUpdateComputer {
	@Autowired
	private ServiceCompany serviceCompany;

	@Autowired
	private ServiceComputer serviceComputer;

	@GetMapping("/UpdateComputer")
	public ModelAndView getUpdateComputerScreen(ComputerFormUpdateDTO computerFormUpdateDTO,
			@RequestParam(required = false) String id) {
		System.out.println("\n\n" + id);
		if (id != null) {
			try {
				computerFormUpdateDTO = MapperComputer
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
		List<CompanyDTO> list = MapperCompany.listCompanyToListCompanyDTO(this.serviceCompany.getListCompany());
		for (CompanyDTO company : list) {
			computerDTOs.put(String.valueOf(company.getId()), company.getName());
		}
		modelView.addObject("listCompany", computerDTOs);
		modelView.getModel().put("ComputerFormUpdateDTO", computerFormUpdateDTO);
		return modelView;
	}

	@PostMapping("/UpdateComputer")
	public ModelAndView updateComputer(ComputerFormUpdateDTO computerFormAddDTO) {
		List<CompanyDTO> listCompany = MapperCompany.listCompanyToListCompanyDTO(this.serviceCompany.getListCompany());
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("UpdateComputer");
		try {
			Computer computer = MapperComputer.computerFormUpdateDTOToComputer(computerFormAddDTO, listCompany);
			this.serviceComputer.addComputer(computer);
		} catch (ErrorSaisieUser errorUser) {
			errorUser.formatEntry();
			return this.getUpdateComputerScreen(computerFormAddDTO, null);

		}
		return modelView;
	}
}
