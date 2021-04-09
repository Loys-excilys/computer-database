package com.excilys.computer.database.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.dto.CompanyStreamDTO;
import com.excilys.computer.database.dto.ComputerStreamDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperCompany;
import com.excilys.computer.database.mappeur.MapperComputer;
import com.excilys.computer.database.service.ServiceCompany;

@RestController
@RequestMapping("/APICompany")
public class APICompany {

	@Autowired
	private ServiceCompany serviceCompany;

	@GetMapping(value = "/{name}", produces = "application/json")
	public ResponseEntity<CompanyStreamDTO> getCompany(@PathVariable String name) {
		Company company = this.serviceCompany.getCompany(name);
		CompanyStreamDTO companyDTO = new MapperCompany().companyToCompanyStreamDTO(company);
		return new ResponseEntity<>(companyDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/page/{numPage}", produces = "application/json")
	public ResponseEntity<List<CompanyStreamDTO>> getCompany(@PathVariable int numPage) {
		List<Company> listCompany = null;
		listCompany = this.serviceCompany.getListCompany(numPage);
		List<CompanyStreamDTO> listDTO = new ArrayList<>();
		for (Company company : listCompany) {
			listDTO.add(new MapperCompany().companyToCompanyStreamDTO(company));
		}
		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/list", produces = "application/json")
	public ResponseEntity<List<CompanyStreamDTO>> getCompany() {
		List<Company> listCompany = null;
		listCompany = this.serviceCompany.getListCompany();
		List<CompanyStreamDTO> listDTO = new ArrayList<>();
		for (Company company : listCompany) {
			listDTO.add(new MapperCompany().companyToCompanyStreamDTO(company));
		}
		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/delete", produces = "application/json")
	public ResponseEntity<String> deleteCompany(@RequestParam int id) {
		this.serviceCompany.deleteCompanyById(id);
		return new ResponseEntity<>("delete effectuer", HttpStatus.OK);
	}
	
	@PostMapping(value = "/add", produces = "application/json", consumes="application/json")
	public ResponseEntity<String> AddCompany(@RequestBody CompanyStreamDTO companyDTO) {
		this.serviceCompany.addCompany(new MapperCompany().companyStreamDTOToCompany(companyDTO));
		return new ResponseEntity<>("Ajout effectuer", HttpStatus.OK);
	}

	@PutMapping(value = "/update", produces = "application/json", consumes="application/json")
	public ResponseEntity<String> UpdateCompany(@RequestBody CompanyStreamDTO companyDTO) {
		this.serviceCompany.updateCompany(new MapperCompany().companyStreamDTOToCompany(companyDTO));
		return new ResponseEntity<>("update effectuer", HttpStatus.OK);
	}
}
