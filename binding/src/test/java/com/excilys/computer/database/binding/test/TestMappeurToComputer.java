package com.excilys.computer.database.binding.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.excilys.computer.database.builder.BuilderCompany;
import com.excilys.computer.database.builder.BuilderComputer;
import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.dto.CompanyDTO;
import com.excilys.computer.database.dto.ComputerFormUpdateDTO;
import com.excilys.computer.database.dto.ComputerFormAddDTO;
import com.excilys.computer.database.dto.ComputerStreamDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.dto.ComputerDatabaseDTO;
import com.excilys.computer.database.mappeur.MapperCompany;
import com.excilys.computer.database.mappeur.MapperComputer;

/**
 * Unit test for simple App.
 */
public class TestMappeurToComputer {

	private Company company;
	private Computer computer;
	
	/**
	 * @return the suite of tests being tested
	 */
	@BeforeEach
	public void setUp() {
		company = new BuilderCompany().addId(0).addName("testCompany").build();
		computer = new BuilderComputer().addId(0).addName("test").addIntroduced(LocalDate.parse("2019-04-20")).addDiscontinued(null).addCompany(company).build();
	}
		
	@Test
	public void ComputerFormAddDTO() {
		List<CompanyDTO> listCompany = new ArrayList<>();
		listCompany.add(new MapperCompany().companyToCompanyDTO(company));
		ComputerFormAddDTO computerFormAddDTO = new ComputerFormAddDTO("test", "2019-04-20", "", "1");
		Computer actualComputer = null;
		try {
			actualComputer = new MapperComputer().computerFormAddDTOToComputer(computerFormAddDTO, listCompany);
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
		assertEquals(computer.getId(), actualComputer.getId());
		assertEquals(computer.getName(), actualComputer.getName());
		assertEquals(computer.getIntroduced().orElse(null), actualComputer.getIntroduced().orElse(null));
		assertEquals(computer.getDiscontinued().orElse(null), actualComputer.getDiscontinued().orElse(null));
		assertEquals(computer.getCompany().orElse(null).getName(), actualComputer.getCompany().orElse(null).getName());
	}
	
	
	@Test
	public void ComputerFormUpdateDTO() {
		List<CompanyDTO> listCompany = new ArrayList<>();
		listCompany.add(new MapperCompany().companyToCompanyDTO(company));
		ComputerFormUpdateDTO computerDTO = new ComputerFormUpdateDTO(0, "test", "2019-04-20", "", "0");
		Computer actualComputer = null;
		try {
			actualComputer = new MapperComputer().computerFormUpdateDTOToComputer(computerDTO, listCompany);
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
		assertEquals(computer.getId(), actualComputer.getId());
		assertEquals(computer.getName(), actualComputer.getName());
		assertEquals(computer.getIntroduced().orElse(null), actualComputer.getIntroduced().orElse(null));
		assertEquals(computer.getDiscontinued().orElse(null), actualComputer.getDiscontinued().orElse(null));
		assertEquals(computer.getCompany().orElse(null).getName(), actualComputer.getCompany().orElse(null).getName());
	}
	
	@Test
	public void ComputerStreamDTO() {
		ComputerStreamDTO computerDTO = new ComputerStreamDTO(0, "test", "2019-04-20", null, new MapperCompany().companyToCompanyStreamDTO(company));
		Computer actualComputer = null;
		try {
			actualComputer = new MapperComputer().computerStreamDTOToComputer(computerDTO);
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
		assertEquals(computer.getId(), actualComputer.getId());
		assertEquals(computer.getName(), actualComputer.getName());
		assertEquals(computer.getIntroduced().orElse(null), actualComputer.getIntroduced().orElse(null));
		assertEquals(computer.getDiscontinued().orElse(null), actualComputer.getDiscontinued().orElse(null));
		assertEquals(computer.getCompany().orElse(null).getName(), actualComputer.getCompany().orElse(null).getName());
	}
	
	@Test
	public void ComputerDatabaseDTO() {
		ComputerDatabaseDTO computerDTO = new ComputerDatabaseDTO(0, "test", LocalDate.parse("2019-04-20"), null, new MapperCompany().companyToCompanyDatabaseDTO(company));
		Computer actualComputer = null;
		try {
			actualComputer = new MapperComputer().computerDatabaseDTOToComputer(computerDTO);
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
		}
		assertEquals(computer.getId(), actualComputer.getId());
		assertEquals(computer.getName(), actualComputer.getName());
		assertEquals(computer.getIntroduced().orElse(null), actualComputer.getIntroduced().orElse(null));
		assertEquals(computer.getDiscontinued().orElse(null), actualComputer.getDiscontinued().orElse(null));
		assertEquals(computer.getCompany().orElse(null).getName(), actualComputer.getCompany().orElse(null).getName());
	}
}
