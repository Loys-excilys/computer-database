package com.excilys.computer.database.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperCompany;
import com.excilys.computer.database.service.ServiceCompany;

@RestController
@RequestMapping("/APICompany")
public class APICompany {

	@Autowired
	private ServiceCompany serviceCompany;

	@GetMapping(value = "/{name}", produces = "application/json")
	public ResponseEntity<?> getCompany(@PathVariable String name) {
		Company company = null;
		try {
			company = this.serviceCompany.getCompany(name);
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
			return new ResponseEntity<>("nom de la company demandée incorrect", HttpStatus.BAD_REQUEST);
		}
		CompanyStreamDTO companyDTO = new MapperCompany().companyToCompanyStreamDTO(company);
		return new ResponseEntity<>(companyDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/page/{numPage}", produces = "application/json")
	public ResponseEntity<?> getCompany(@PathVariable int numPage) {
		List<Company> listCompany = null;
		try {
			listCompany = this.serviceCompany.getListCompany(numPage);
			if(listCompany.size() == 0) {
				throw new ErrorSaisieUser(this.getClass());
			}
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
			return new ResponseEntity<>("aucun résultat trouvé, vérifiez le numéro de page", HttpStatus.BAD_REQUEST);
		}
		
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

	@DeleteMapping(value = "/delete", produces = "application/json")
	public ResponseEntity<String> deleteCompany(@RequestParam int id) {
		try {
			this.serviceCompany.deleteCompanyById(id);
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
			return new ResponseEntity<>("error delete, verifiez les données envoyées", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("delete effectuer", HttpStatus.OK);
	}
	
	@PostMapping(value = "/add", produces = "application/json", consumes="application/json")
	public ResponseEntity<String> AddCompany(@RequestBody CompanyStreamDTO companyDTO) {
		try {
			this.serviceCompany.addCompany(new MapperCompany().companyStreamDTOToCompany(companyDTO));
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
			return new ResponseEntity<>("Ajout non effectué, veuillez vérifier les informations données", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Ajout effectuer", HttpStatus.OK);
	}

	@PutMapping(value = "/update", produces = "application/json", consumes="application/json")
	public ResponseEntity<String> UpdateCompany(@RequestBody CompanyStreamDTO companyDTO) {
		try {
			this.serviceCompany.updateCompany(new MapperCompany().companyStreamDTOToCompany(companyDTO));
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
			return new ResponseEntity<>("aucune modification n'a été effectué ou à rencontrer un problème, veuillez vérifier les informations données", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("update effectuer", HttpStatus.OK);
	}
}
