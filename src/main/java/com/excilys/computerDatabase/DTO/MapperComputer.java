package com.excilys.computerDatabase.DTO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.data.ComputerFactory;
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
	
	public static ComputerDTO requestToComputerDTO(HttpServletRequest request) {
		ComputerDTO computerDTO = new ComputerDTO(request.getParameter("computerName"),
				request.getParameter("dateIntroduced"),
				request.getParameter("dateDiscontinued"),
				request.getParameter("companyName"));
		return computerDTO;
	}
	
	public static Computer ComputerDTOToComputer(ComputerDTO computerDTO, List<CompanyDTO> listCompany) throws ErrorSaisieUser {
		Computer computer = new ComputerFactory().getComputer(computerDTO.getName(),
				computerDTO.getIntroduced(),
				computerDTO.getDiscontinued(),
				computerDTO.getCompany().compareTo("") != 0 ? MapperCompany.companyDTOToCompany(listCompany.get(Integer.parseInt(computerDTO.getCompany()))) : null);
		return computer;
	}
}
