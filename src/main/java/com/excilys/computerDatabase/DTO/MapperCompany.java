package com.excilys.computerDatabase.DTO;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.data.Company;

public class MapperCompany {
	
	public static CompanyDTO companyToCompanyDTO(Company company) {
		CompanyDTO companyDTO = new CompanyDTO(company.getId(), company.getName());
		return companyDTO;
	}
	public static List<CompanyDTO> ListComputerToListComputerDTO(List<Company> listCompany){
		List<CompanyDTO> listCompanyDTO= new ArrayList<CompanyDTO>();
		for(Company company : listCompany) {
			listCompanyDTO.add(companyToCompanyDTO(company));
		}
		return listCompanyDTO;
	}
	
	public static Company companyDTOToCompany(CompanyDTO companyDTO) {
		Company company = new Company(companyDTO.getId(), companyDTO.getName());
		return company;
	}
}
