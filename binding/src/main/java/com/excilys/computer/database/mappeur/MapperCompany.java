package com.excilys.computer.database.mappeur;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computer.database.builder.BuilderCompany;
import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.dto.CompanyDTO;
import com.excilys.computer.database.dto.CompanyDatabaseDTO;
import com.excilys.computer.database.dto.CompanyStreamDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.validator.ValidateurCompany;

public class MapperCompany {

	public MapperCompany() {
	}

	public CompanyDTO companyToCompanyDTO(Company company) {
		return new CompanyDTO(company.getId(), company.getName(), company.getLogo());
	}

	public List<CompanyDTO> listCompanyToListCompanyDTO(List<Company> listCompany) {
		List<CompanyDTO> listCompanyDTO = new ArrayList<>();
		for (Company company : listCompany) {
			listCompanyDTO.add(companyToCompanyDTO(company));
		}
		return listCompanyDTO;
	}

	public Company companyDTOToCompany(CompanyDTO companyDTO) throws ErrorSaisieUser {
		return new ValidateurCompany().Valide(new BuilderCompany().addId(companyDTO.getId()).addName(companyDTO.getName()).addLogo(companyDTO.getLogo()).build());
	}
	
	public Company companyDatabaseDTOToCompany(CompanyDatabaseDTO companyDTO) throws ErrorSaisieUser {
		if(companyDTO != null) {
			return new ValidateurCompany().Valide(new BuilderCompany().addId(companyDTO.getId()).addName(companyDTO.getName()).addLogo(companyDTO.getLogo()).build());
		}
		return null;
	}
	public CompanyDatabaseDTO companyToCompanyDatabaseDTO(Company company) {
		if(company != null) {
			return new CompanyDatabaseDTO(company.getId(), company.getName(), company.getLogo());
		}else {
			return null;
		}
		
	}
	
	public Company companyStreamDTOToCompany(CompanyStreamDTO companyDTO) throws ErrorSaisieUser {
		if(companyDTO != null) {
			return new ValidateurCompany().Valide(new BuilderCompany().addId(companyDTO.getId()).addName(companyDTO.getName()).addLogo(companyDTO.getLogo()).build());
		}
		return null;
	}
	public CompanyStreamDTO companyToCompanyStreamDTO(Company company) {
		if(company != null) {
			return new CompanyStreamDTO(company.getId(), company.getName(), company.getLogo());
		}else {
			return null;
		}
		
	}
	
}
