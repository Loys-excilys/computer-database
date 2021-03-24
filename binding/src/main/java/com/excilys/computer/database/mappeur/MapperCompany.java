package com.excilys.computer.database.mappeur;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.dto.CompanyDTO;
import com.excilys.computer.database.dto.CompanyDatabaseDTO;

public class MapperCompany {

	public MapperCompany() {
	}

	public static CompanyDTO companyToCompanyDTO(Company company) {
		return new CompanyDTO(company.getId(), company.getName());
	}

	public static List<CompanyDTO> listCompanyToListCompanyDTO(List<Company> listCompany) {
		List<CompanyDTO> listCompanyDTO = new ArrayList<>();
		for (Company company : listCompany) {
			listCompanyDTO.add(companyToCompanyDTO(company));
		}
		return listCompanyDTO;
	}

	public static Company companyDTOToCompany(CompanyDTO companyDTO) {
		return new Company(companyDTO.getId(), companyDTO.getName());
	}
	
	public Company companyDatabaseDTOToCompany(Optional<CompanyDatabaseDTO> companyDTO) {
		if(companyDTO.isPresent()) {
			return new Company(companyDTO.get().getId(), companyDTO.get().getName());
		}else {
			return null;
		}
	}
	public CompanyDatabaseDTO companyToCompanyDatabaseDTO(Optional<Company> company) {
		if(company.isPresent()) {
			return new CompanyDatabaseDTO(company.get().getId(), company.get().getName());
		}else {
			return null;
		}
		
	}
	
}
