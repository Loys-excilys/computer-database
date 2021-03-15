package com.excilys.computer.database.mappeur;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.excilys.computer.database.builder.ComputerBuilder;
import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.dto.CompanyDTO;
import com.excilys.computer.database.dto.ComputerDTO;
import com.excilys.computer.database.dto.ComputerFormAddDTO;
import com.excilys.computer.database.dto.ComputerFormUpdateDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.validator.ValidateurComputer;

public class MapperComputer {

	private MapperComputer() {
	}

	public static ComputerDTO computerToComputerDTO(Computer computer) {
		Optional<LocalDate> introduced = computer.getIntroduced();
		Optional<LocalDate> discontinued = computer.getDiscontinued();
		Optional<Company> company = computer.getCompany();

		return new ComputerDTO(computer.getId(), computer.getName(),
				introduced.isPresent() ? introduced.get().toString() : "",
				discontinued.isPresent() ? discontinued.get().toString() : "",
				company.isPresent() ? company.get().getName() : "");
	}
	
	public static ComputerFormUpdateDTO computerToComputerFormUpdateDTO(Computer computer) {
		Optional<LocalDate> introduced = computer.getIntroduced();
		Optional<LocalDate> discontinued = computer.getDiscontinued();
		Optional<Company> company = computer.getCompany();

		return new ComputerFormUpdateDTO(computer.getId(), computer.getName(),
				introduced.isPresent() ? introduced.get().toString() : "",
				discontinued.isPresent() ? discontinued.get().toString() : "",
				company.isPresent() ? String.valueOf(company.get().getId()) : "");
	}

	public static List<ComputerDTO> listComputerToListComputerDTO(List<Computer> listComputer) {
		List<ComputerDTO> listComputerDTO = new ArrayList<>();
		for (Computer computer : listComputer) {
			listComputerDTO.add(computerToComputerDTO(computer));
		}
		return listComputerDTO;
	}

	public static ComputerFormAddDTO requestToComputerFormAddDTO(HttpServletRequest request) {
		return new ComputerFormAddDTO(request.getParameter("computerName"), request.getParameter("dateIntroduced"),
				request.getParameter("dateDiscontinued"), request.getParameter("companyName"));
	}

	public static ComputerFormUpdateDTO requestToComputerFormUpdateDTO(HttpServletRequest request) {
		return new ComputerFormUpdateDTO(Long.parseLong(request.getSession().getAttribute("idComputer").toString()),
				request.getParameter("computerName"), request.getParameter("dateIntroduced"),
				request.getParameter("dateDiscontinued"), request.getParameter("companyName"));
	}

	public static Computer computerFormAddDTOToComputer(ComputerFormAddDTO computerFormAddDTO,
			List<CompanyDTO> listCompany) throws ErrorSaisieUser {

		return ValidateurComputer.getInstance()
				.getValidate(new ComputerBuilder().addName(computerFormAddDTO.getName())
						.addIntroduced(computerFormAddDTO.getIntroduced().compareTo("") != 0
								? Optional.of(LocalDate.parse(computerFormAddDTO.getIntroduced()))
								: Optional.empty())
						.addDiscontinued(computerFormAddDTO.getDiscontinued().compareTo("") != 0
								? Optional.of(LocalDate.parse(computerFormAddDTO.getDiscontinued()))
								: Optional.empty())
						.addCompany(computerFormAddDTO.getCompanyId().compareTo("") != 0
								? Optional.ofNullable(MapperCompany.companyDTOToCompany(
										listCompany.get(Integer.parseInt(computerFormAddDTO.getCompanyId()) - 1)))
								: Optional.empty())
						.getComputer());
	}

	public static Computer computerFormUpdateDTOToComputer(ComputerFormUpdateDTO computerFormUpdateDTO,
			List<CompanyDTO> listCompany) throws ErrorSaisieUser {

		return ValidateurComputer.getInstance()
				.getValidate(new ComputerBuilder().addId(computerFormUpdateDTO.getId())
						.addName(computerFormUpdateDTO.getName())
						.addIntroduced(computerFormUpdateDTO.getIntroduced().compareTo("") != 0
								? Optional.of(LocalDate.parse(computerFormUpdateDTO.getIntroduced()))
								: Optional.empty())
						.addDiscontinued(computerFormUpdateDTO.getDiscontinued().compareTo("") != 0
								? Optional.of(LocalDate.parse(computerFormUpdateDTO.getDiscontinued()))
								: Optional.empty())
						.addCompany(computerFormUpdateDTO.getCompanyId().compareTo("") != 0
								? Optional.ofNullable(MapperCompany.companyDTOToCompany(
										listCompany.get(Integer.parseInt(computerFormUpdateDTO.getCompanyId()) - 1)))
								: Optional.empty())
						.getComputer());
	}
}
