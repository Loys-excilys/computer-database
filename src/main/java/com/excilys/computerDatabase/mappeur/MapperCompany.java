package com.excilys.computerDatabase.mappeur;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.dto.CompanyDTO;

public class MapperCompany {
	
	private MapperCompany() {}
	
	public static CompanyDTO companyToCompanyDTO(Company company) {
		return new CompanyDTO(company.getId(), company.getName());
	}
	public static List<CompanyDTO> listCompanyToListCompanyDTO(List<Company> listCompany){
		List<CompanyDTO> listCompanyDTO= new ArrayList<>();
		for(Company company : listCompany) {
			listCompanyDTO.add(companyToCompanyDTO(company));
		}
		return listCompanyDTO;
	}
	
	public static Company companyDTOToCompany(CompanyDTO companyDTO) {
		return new Company(companyDTO.getId(), companyDTO.getName());
	}
}
