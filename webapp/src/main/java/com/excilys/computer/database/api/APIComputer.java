package com.excilys.computer.database.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computer.database.builder.ComputerBuilder;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.dto.ComputerDatabaseDTO;
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
	public List<ComputerDatabaseDTO> getComputer(@PathVariable int numPage, @PathVariable int maxEntry) {
		Page page = new Page();
		page.setMaxPrint(maxEntry);
		page.setPage(numPage);
		List<Computer> listComputer = null;
		try {
			listComputer = this.serviceComputer.getListComputer(page);
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
		List<ComputerDatabaseDTO> listDTO = new ArrayList<>();
		for (Computer computer : listComputer) {
			listDTO.add(new MapperComputer().computerToComputerDatabaseDTO(computer));
		}
		return listDTO;
	}

	@GetMapping(value = "/search/page/{numPage}/{maxEntry}", produces = "application/json")
	public List<ComputerDatabaseDTO> getSearchComputer(@RequestParam String search, @PathVariable int numPage,
			@PathVariable int maxEntry) {
		Page page = new Page();
		page.setMaxPrint(maxEntry);
		page.setPage(numPage);
		List<Computer> listComputer = null;
		try {
			listComputer = this.serviceComputer.getSearchComputer(search, page);
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
		List<ComputerDatabaseDTO> listDTO = new ArrayList<>();
		for (Computer computer : listComputer) {
			listDTO.add(new MapperComputer().computerToComputerDatabaseDTO(computer));
		}
		return listDTO;
	}

	@GetMapping(value = "/order/page/{numPage}/{maxEntry}", produces = "application/json")
	public List<ComputerDatabaseDTO> getComputerOrder(@RequestParam String sort, @RequestParam String orderField, @PathVariable int numPage,
			@PathVariable int maxEntry) {
		Page page = new Page();
		page.setMaxPrint(maxEntry);
		page.setPage(numPage);
		List<Computer> listComputer = null;
		try {
			listComputer = this.serviceComputer.getListComputerOrder(orderField, sort, page);
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
		List<ComputerDatabaseDTO> listDTO = new ArrayList<>();
		for (Computer computer : listComputer) {
			listDTO.add(new MapperComputer().computerToComputerDatabaseDTO(computer));
		}
		return listDTO;
	}
	
	@GetMapping(value = "/search/order/page/{numPage}/{maxEntry}", produces = "application/json")
	public List<ComputerDatabaseDTO> getSearchComputerOrder(@RequestParam String sort, @RequestParam String orderField, @RequestParam String search, @PathVariable int numPage,
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
		List<ComputerDatabaseDTO> listDTO = new ArrayList<>();
		for (Computer computer : listComputer) {
			listDTO.add(new MapperComputer().computerToComputerDatabaseDTO(computer));
		}
		return listDTO;
	}
	
	
	@PostMapping(value = "/add", produces = "application/json")
	public void AddComputer(@RequestParam String Name, @RequestParam LocalDate introduced, @RequestParam LocalDate discontinued, @RequestParam String company) {
		this.serviceComputer.addComputer(new ComputerBuilder().addName(Name).addIntroduced(introduced)
				.addDiscontinued(discontinued).addCompany(this.serviceCompany.getCompany(company)).getComputer());
	}

	
	@PostMapping(value = "/update", produces = "application/json")
	public void UpdateComputer(@RequestParam String Name, @RequestParam LocalDate introduced, @RequestParam LocalDate discontinued, @RequestParam String company) {
		this.serviceComputer.updateComputer(new ComputerBuilder().addName(Name).addIntroduced(introduced)
				.addDiscontinued(discontinued).addCompany(this.serviceCompany.getCompany(company)).getComputer());
	}
	
	@PostMapping(value = "/delete", produces = "application/json")
	public void UpdateComputer(@RequestParam int id) {
		this.serviceComputer.deleteComputerById(id);
	}
}
