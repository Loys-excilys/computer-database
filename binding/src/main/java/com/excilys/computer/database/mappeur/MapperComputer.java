package com.excilys.computer.database.mappeur;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.excilys.computer.database.builder.BuilderComputer;
import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.dto.CompanyDTO;
import com.excilys.computer.database.dto.ComputerDTO;
import com.excilys.computer.database.dto.ComputerDatabaseDTO;
import com.excilys.computer.database.dto.ComputerFormAddDTO;
import com.excilys.computer.database.dto.ComputerFormUpdateDTO;
import com.excilys.computer.database.dto.ComputerStreamDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.validator.ValidateurComputer;

public class MapperComputer {

	public ComputerDTO computerToComputerDTO(Computer computer) {
		return new ComputerDTO(computer.getId(), computer.getName(),
				computer.getIntroduced().isPresent() ? computer.getIntroduced().get().toString() : "",
				computer.getDiscontinued().isPresent() ? computer.getDiscontinued().get().toString() : "",
				computer.getCompany().isPresent() ? computer.getCompany().get().getName() : "");
	}

	public ComputerFormUpdateDTO computerToComputerFormUpdateDTO(Computer computer) {
		Optional<LocalDate> introduced = computer.getIntroduced();
		Optional<LocalDate> discontinued = computer.getDiscontinued();
		Optional<Company> company = computer.getCompany();

		return new ComputerFormUpdateDTO(computer.getId(), computer.getName(),
				introduced.isPresent() ? introduced.get().toString() : "",
				discontinued.isPresent() ? discontinued.get().toString() : "",
				company.isPresent() ? String.valueOf(company.get().getId()) : "");
	}

	public List<ComputerDTO> listComputerToListComputerDTO(List<Computer> listComputer) {
		List<ComputerDTO> listComputerDTO = new ArrayList<>();
		for (Computer computer : listComputer) {
			listComputerDTO.add(computerToComputerDTO(computer));
		}
		return listComputerDTO;
	}

	public ComputerFormAddDTO requestToComputerFormAddDTO(HttpServletRequest request) {
		return new ComputerFormAddDTO(request.getParameter("computerName"), request.getParameter("dateIntroduced"),
				request.getParameter("dateDiscontinued"), request.getParameter("companyName"));
	}

	public ComputerFormUpdateDTO requestToComputerFormUpdateDTO(HttpServletRequest request) {
		return new ComputerFormUpdateDTO(Long.parseLong(request.getSession().getAttribute("idComputer").toString()),
				request.getParameter("computerName"), request.getParameter("dateIntroduced"),
				request.getParameter("dateDiscontinued"), request.getParameter("companyName"));
	}

	public Computer computerFormAddDTOToComputer(ComputerFormAddDTO computerFormAddDTO, List<CompanyDTO> listCompany)
			throws ErrorSaisieUser {

		return ValidateurComputer.getInstance()
				.getValidate(new BuilderComputer().addName(computerFormAddDTO.getName())
						.addIntroduced(computerFormAddDTO.getIntroduced().compareTo("") != 0
								? LocalDate.parse(computerFormAddDTO.getIntroduced())
								: null)
						.addDiscontinued(computerFormAddDTO.getDiscontinued().compareTo("") != 0
								? LocalDate.parse(computerFormAddDTO.getDiscontinued())
								: null)
						.addCompany(computerFormAddDTO.getCompanyId().compareTo("") != 0
								? new MapperCompany().companyDTOToCompany(
										listCompany.get(Integer.parseInt(computerFormAddDTO.getCompanyId()) - 1))
								: null)
						.build());
	}

	public Computer computerFormUpdateDTOToComputer(ComputerFormUpdateDTO computerFormUpdateDTO,
			List<CompanyDTO> listCompany) throws ErrorSaisieUser {

		return ValidateurComputer.getInstance()
				.getValidate(
						new BuilderComputer().addId(computerFormUpdateDTO.getId())
								.addName(computerFormUpdateDTO.getName())
								.addIntroduced(computerFormUpdateDTO.getIntroduced().compareTo("") != 0
										? LocalDate.parse(computerFormUpdateDTO.getIntroduced())
										: null)
								.addDiscontinued(computerFormUpdateDTO.getDiscontinued().compareTo("") != 0
										? LocalDate.parse(computerFormUpdateDTO.getDiscontinued())
										: null)
								.addCompany(computerFormUpdateDTO.getCompanyId().compareTo("") != 0
										? new MapperCompany().companyDTOToCompany(
												listCompany.get(Integer.parseInt(computerFormUpdateDTO.getCompanyId())))
										: null)
								.build());
	}

	public Computer computerDatabaseDTOToComputer(ComputerDatabaseDTO computerDTO) throws ErrorSaisieUser {
		return ValidateurComputer.getInstance()
				.getValidate(new BuilderComputer().addName(computerDTO.getName()).addId(computerDTO.getId())
						.addIntroduced(computerDTO.getIntroduced()).addDiscontinued(computerDTO.getDiscontinued())
						.addCompany(new MapperCompany().companyDatabaseDTOToCompany(computerDTO.getCompany())).build());
	}

	public ComputerDatabaseDTO computerToComputerDatabaseDTO(Computer computer) {
		return new ComputerDatabaseDTO(computer.getId(), computer.getName(), computer.getIntroduced().orElse(null),
				computer.getDiscontinued().orElse(null),
				new MapperCompany().companyToCompanyDatabaseDTO(computer.getCompany().orElse(null)));
	}

	public Computer computerStreamDTOToComputer(ComputerStreamDTO computerDTO) throws ErrorSaisieUser {
		return ValidateurComputer.getInstance().getValidate(new BuilderComputer().addName(computerDTO.getName())
				.addId(computerDTO.getId())
				.addIntroduced(
						computerDTO.getIntroduced() != null ? LocalDate.parse(computerDTO.getIntroduced()) : null)
				.addDiscontinued(
						computerDTO.getDiscontinued() != null ? LocalDate.parse(computerDTO.getDiscontinued()) : null)
				.addCompany(new MapperCompany().companyStreamDTOToCompany(computerDTO.getCompany())).build());
	}

	public ComputerStreamDTO computerToComputerStreamDTO(Computer computer) {
		return new ComputerStreamDTO(computer.getId(), computer.getName(),
				computer.getIntroduced().orElse(null) != null ? computer.getIntroduced().get().toString() : null,
				computer.getDiscontinued().orElse(null) != null ? computer.getDiscontinued().get().toString() : null,
				new MapperCompany().companyToCompanyStreamDTO(computer.getCompany().orElse(null)));
	}
}
