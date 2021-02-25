package com.excilys.computerDatabase.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.data.ComputerBuilder;
import com.excilys.computerDatabase.data.ValidateurComputer;
import com.excilys.computerDatabase.error.ErrorSaisieUser;

public abstract class MapperComputer {

	public static ComputerDTO computerToComputerDTO(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO(computer.getName(),
				computer.getIntroduced() != null ? computer.getIntroduced().toString() : "",
				computer.getDiscontinued() != null ? computer.getDiscontinued().toString() : "",
				computer.getCompany().getName());
		return computerDTO;
	}
	public static List<ComputerDTO> ListComputerToListComputerDTO(List<Computer> listComputer){
		List<ComputerDTO> listComputerDTO= new ArrayList<ComputerDTO>();
		for(Computer computer : listComputer) {
			listComputerDTO.add(computerToComputerDTO(computer));
		}
		return listComputerDTO;
	}
	
	public static ComputerFormAddDTO requestToComputerFormAddDTO(HttpServletRequest request) {
		ComputerFormAddDTO computerFormAddDTO = new ComputerFormAddDTO(request.getParameter("computerName"),
				request.getParameter("dateIntroduced"),
				request.getParameter("dateDiscontinued"),
				request.getParameter("companyName"));
		return computerFormAddDTO;
	}
	
	public static Computer ComputerFormAddDTOToComputer(ComputerFormAddDTO computerFormAddDTO, List<CompanyDTO> listCompany) throws ErrorSaisieUser {
		
		Computer computer = ValidateurComputer.getValidate(new ComputerBuilder()
				.addName(computerFormAddDTO.getName())
				.addIntroduced(computerFormAddDTO.getIntroduced().compareTo("") != 0 ? LocalDate.parse(computerFormAddDTO.getIntroduced()) : null)
				.addDiscontinued(computerFormAddDTO.getDiscontinued().compareTo("") != 0 ? LocalDate.parse(computerFormAddDTO.getDiscontinued()) : null)
				.addCompany(computerFormAddDTO.getCompanyId().compareTo("") != 0 ? MapperCompany.companyDTOToCompany(listCompany.get(Integer.parseInt(computerFormAddDTO.getCompanyId()))) : null)
				.getComputer());
		return computer;
	}
}
