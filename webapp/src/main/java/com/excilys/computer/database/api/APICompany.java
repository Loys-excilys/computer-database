package com.excilys.computer.database.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.dto.CompanyDatabaseDTO;
import com.excilys.computer.database.mappeur.MapperCompany;
import com.excilys.computer.database.service.ServiceCompany;

@RestController
@RequestMapping("/APICompany")
public class APICompany {

	@Autowired
	private ServiceCompany serviceCompany;

	@GetMapping(value = "/page/{numPage}", produces = "application/json")
	public List<CompanyDatabaseDTO> getCompany(@PathVariable int numPage) {
		List<Company> listCompany = null;
		listCompany = this.serviceCompany.getListCompany(numPage);
		List<CompanyDatabaseDTO> listDTO = new ArrayList<>();
		for (Company company : listCompany) {
			listDTO.add(new MapperCompany().companyToCompanyDatabaseDTO(company));
		}
		return listDTO;
	}

	@GetMapping(value = "/list", produces = "application/json")
	public List<CompanyDatabaseDTO> getCompany() {
		List<Company> listCompany = null;
		listCompany = this.serviceCompany.getListCompany();
		List<CompanyDatabaseDTO> listDTO = new ArrayList<>();
		for (Company company : listCompany) {
			listDTO.add(new MapperCompany().companyToCompanyDatabaseDTO(company));
		}
		return listDTO;
	}

	@GetMapping(value = "/delete", produces = "application/json")
	public void deleteCompany(@RequestParam int id) {
		this.serviceCompany.deleteCompanyById(id);
	}
}
