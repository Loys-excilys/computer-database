package com.excilys.computer.database.api;

import java.time.LocalDate;
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

import com.excilys.computer.database.builder.ComputerBuilder;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.dto.ComputerDatabaseDTO;
import com.excilys.computer.database.dto.ComputerStreamDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperComputer;
import com.excilys.computer.database.service.ServiceCompany;
import com.excilys.computer.database.service.ServiceComputer;

@RestController
@RequestMapping("/APIComputer")
public class APIComputer {

	@Autowired
	private ServiceComputer serviceComputer;

	@Autowired
	private ServiceCompany serviceCompany;

	@GetMapping(value = "/page/{numPage}/{maxEntry}", produces = "application/json")
	public ResponseEntity<List<ComputerStreamDTO>> getComputer(@PathVariable int numPage, @PathVariable int maxEntry) {
		Page page = new Page();
		page.setMaxPrint(maxEntry);
		page.setPage(numPage);
		List<Computer> listComputer = null;
		try {
			listComputer = this.serviceComputer.getListComputer(page);
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
		List<ComputerStreamDTO> listDTO = new ArrayList<>();
		for (Computer computer : listComputer) {
			listDTO.add(new MapperComputer().computerToComputerStreamDTO(computer));
		}

		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/search/page/{numPage}/{maxEntry}", produces = "application/json")
	public ResponseEntity<List<ComputerStreamDTO>> getSearchComputer(@RequestParam String search,
			@PathVariable int numPage, @PathVariable int maxEntry) {
		Page page = new Page();
		page.setMaxPrint(maxEntry);
		page.setPage(numPage);
		List<Computer> listComputer = null;
		try {
			listComputer = this.serviceComputer.getSearchComputer(search, page);
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
		List<ComputerStreamDTO> listDTO = new ArrayList<>();
		for (Computer computer : listComputer) {
			listDTO.add(new MapperComputer().computerToComputerStreamDTO(computer));
		}
		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/order/page/{numPage}/{maxEntry}", produces = "application/json")
	public ResponseEntity<List<ComputerStreamDTO>> getComputerOrder(@RequestParam String sort,
			@RequestParam String orderField, @PathVariable int numPage, @PathVariable int maxEntry) {
		Page page = new Page();
		page.setMaxPrint(maxEntry);
		page.setPage(numPage);
		List<Computer> listComputer = null;
		try {
			listComputer = this.serviceComputer.getListComputerOrder(orderField, sort, page);
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
		List<ComputerStreamDTO> listDTO = new ArrayList<>();
		for (Computer computer : listComputer) {
			listDTO.add(new MapperComputer().computerToComputerStreamDTO(computer));
		}
		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/search/order/page/{numPage}/{maxEntry}", produces = "application/json")
	public ResponseEntity<List<ComputerStreamDTO>> getSearchComputerOrder(@RequestParam String sort,
			@RequestParam String orderField, @RequestParam String search, @PathVariable int numPage,
			@PathVariable int maxEntry) {
		Page page = new Page();
		page.setMaxPrint(maxEntry);
		page.setPage(numPage);
		List<Computer> listComputer = null;
		try {
			listComputer = this.serviceComputer.getResearchComputerOrder(search, orderField, sort, page);
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
		List<ComputerStreamDTO> listDTO = new ArrayList<>();
		for (Computer computer : listComputer) {
			listDTO.add(new MapperComputer().computerToComputerStreamDTO(computer));
		}
		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/count", produces = "application/json")
	public ResponseEntity<Long> getCountComputer() {
		return new ResponseEntity<>(this.serviceComputer.getNumberComputer(), HttpStatus.OK);
	}

	@GetMapping(value = "/count/search", produces = "application/json")
	public ResponseEntity<Long> getSearchComputer(@RequestParam String search) {
		return new ResponseEntity<>(this.serviceComputer.getSearchNumberComputer(search), HttpStatus.OK);
	}

	@PostMapping(value = "/add", produces = "application/json")
	public ResponseEntity<String> AddComputer(@RequestBody ComputerStreamDTO computerDTO) {
		try {
			this.serviceComputer.addComputer(new MapperComputer().computerStreamDTOToComputer(computerDTO));
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Ajout effectuer", HttpStatus.OK);
	}

	@PutMapping(value = "/update", produces = "application/json")
	public ResponseEntity<String> UpdateComputer(@RequestBody ComputerStreamDTO computerDTO) {
		try {
			this.serviceComputer.updateComputer(new MapperComputer().computerStreamDTOToComputer(computerDTO));
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("update effectuer", HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete", produces = "application/json")
	public ResponseEntity<String> UpdateComputer(@RequestParam int id) {
		this.serviceComputer.deleteComputerById(id);
		return new ResponseEntity<>("delete effectuer", HttpStatus.OK);
	}
}
