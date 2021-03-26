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
	
	public Company companyDatabaseDTOToCompany(CompanyDatabaseDTO companyDTO) {
		if(companyDTO != null) {
			return new Company(companyDTO.getId(), companyDTO.getName());
		}
		return null;
	}
	public CompanyDatabaseDTO companyToCompanyDatabaseDTO(Company company) {
		if(company != null) {
			return new CompanyDatabaseDTO(company.getId(), company.getName());
		}else {
			return null;
		}
		
	}
	
}
