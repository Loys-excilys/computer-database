package com.excilys.computer.database.binding.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.excilys.computer.database.builder.ComputerBuilder;
import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.dto.ComputerDTO;
import com.excilys.computer.database.dto.ComputerFormUpdateDTO;
import com.excilys.computer.database.dto.ComputerFormAddDTO;
import com.excilys.computer.database.dto.ComputerStreamDTO;
import com.excilys.computer.database.dto.ComputerDatabaseDTO;
import com.excilys.computer.database.mappeur.MapperCompany;
import com.excilys.computer.database.mappeur.MapperComputer;

/**
 * Unit test for simple App.
 */
public class MappeurComputerTo {

	private Company Company;
	private Computer Computer;
	
	/**
	 * @return the suite of tests being tested
	 */
	@BeforeEach
	public void setUp() {
		Company = new Company(0, "testCompany");
		Computer = new ComputerBuilder().addId(0).addName("test").addIntroduced(LocalDate.parse("2019-04-20")).addDiscontinued(null).addCompany(Company).getComputer();
	}
	
	@Test
	public void ComputerDTO() {
		ComputerDTO computerDTO = new ComputerDTO(0, "test", "2019-04-20", "", "testCompany");
		ComputerDTO actiualComputerDTO = new MapperComputer().computerToComputerDTO(Computer);
		assertEquals(computerDTO.getId(), actiualComputerDTO.getId());
		assertEquals(computerDTO.getName(), actiualComputerDTO.getName());
		assertEquals(computerDTO.getIntroduced(), actiualComputerDTO.getIntroduced());
		assertEquals(computerDTO.getDiscontinued(), actiualComputerDTO.getDiscontinued());
		assertEquals(computerDTO.getCompanyName(), actiualComputerDTO.getCompanyName());
	}
	
	@Test
	public void requestToComputerFormAddDTO() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		Mockito.lenient().when(request.getParameter("computerName")).thenReturn("test");
		Mockito.lenient().when(request.getParameter("dateIntroduced")).thenReturn("2019-04-20");
		Mockito.lenient().when(request.getParameter("dateDiscontinued")).thenReturn("");
		Mockito.lenient().when(request.getParameter("companyName")).thenReturn("1");
		ComputerFormAddDTO computerFormAddDTO = new ComputerFormAddDTO("test", "2019-04-20", "", "1");
		ComputerFormAddDTO actiualComputerDTO = new MapperComputer().requestToComputerFormAddDTO(request);
		assertEquals(computerFormAddDTO.getName(), actiualComputerDTO.getName());
		assertEquals(computerFormAddDTO.getIntroduced(), actiualComputerDTO.getIntroduced());
		assertEquals(computerFormAddDTO.getDiscontinued(), actiualComputerDTO.getDiscontinued());
		assertEquals(computerFormAddDTO.getCompanyId(), actiualComputerDTO.getCompanyId());
	}
	
	@Test
	public void requestToComputerFormUpdateDTO() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		Mockito.lenient().when(request.getSession()).thenReturn(mock(HttpSession.class));
		Mockito.lenient().when(request.getSession().getAttribute("idComputer")).thenReturn("0");
		Mockito.lenient().when(request.getParameter("computerName")).thenReturn("test");
		Mockito.lenient().when(request.getParameter("dateIntroduced")).thenReturn("2019-04-20");
		Mockito.lenient().when(request.getParameter("dateDiscontinued")).thenReturn("");
		Mockito.lenient().when(request.getParameter("companyName")).thenReturn("1");
		
		ComputerFormUpdateDTO computerFormAddDTO = new ComputerFormUpdateDTO(0, "test", "2019-04-20", "", "1");
		ComputerFormUpdateDTO actiualComputerDTO = new MapperComputer().requestToComputerFormUpdateDTO(request);
		assertEquals(computerFormAddDTO.getName(), actiualComputerDTO.getName());
		assertEquals(computerFormAddDTO.getIntroduced(), actiualComputerDTO.getIntroduced());
		assertEquals(computerFormAddDTO.getDiscontinued(), actiualComputerDTO.getDiscontinued());
		assertEquals(computerFormAddDTO.getCompanyId(), actiualComputerDTO.getCompanyId());
	}
	
	@Test
	public void ComputerFormUpdateDTO() {
		ComputerFormUpdateDTO computerDTO = new ComputerFormUpdateDTO(0, "test", "2019-04-20", "", "0");
		ComputerFormUpdateDTO actiualComputerDTO = new MapperComputer().computerToComputerFormUpdateDTO(Computer);
		assertEquals(computerDTO.getId(), actiualComputerDTO.getId());
		assertEquals(computerDTO.getName(), actiualComputerDTO.getName());
		assertEquals(computerDTO.getIntroduced(), actiualComputerDTO.getIntroduced());
		assertEquals(computerDTO.getDiscontinued(), actiualComputerDTO.getDiscontinued());
		assertEquals(computerDTO.getCompanyId(), actiualComputerDTO.getCompanyId());
	}
	
	@Test
	public void ComputerStreamDTO() {
		ComputerStreamDTO computerDTO = new ComputerStreamDTO(0, "test", "2019-04-20", null, new MapperCompany().companyToCompanyStreamDTO(Company));
		ComputerStreamDTO actiualComputerDTO = new MapperComputer().computerToComputerStreamDTO(Computer);
		assertEquals(computerDTO.getId(), actiualComputerDTO.getId());
		assertEquals(computerDTO.getName(), actiualComputerDTO.getName());
		assertEquals(computerDTO.getIntroduced(), actiualComputerDTO.getIntroduced());
		assertEquals(computerDTO.getDiscontinued(), actiualComputerDTO.getDiscontinued());
		assertEquals(computerDTO.getCompany().getId(), actiualComputerDTO.getCompany().getId());
		assertEquals(computerDTO.getCompany().getName(), actiualComputerDTO.getCompany().getName());
	}
	
	@Test
	public void ComputerDatabaseDTO() {
		ComputerDatabaseDTO computerDTO = new ComputerDatabaseDTO(0, "test", LocalDate.parse("2019-04-20"), null, new MapperCompany().companyToCompanyDatabaseDTO(Company));
		ComputerDatabaseDTO actiualComputerDTO = new MapperComputer().computerToComputerDatabaseDTO(Computer);
		assertEquals(computerDTO.getId(), actiualComputerDTO.getId());
		assertEquals(computerDTO.getName(), actiualComputerDTO.getName());
		assertEquals(computerDTO.getIntroduced(), actiualComputerDTO.getIntroduced());
		assertEquals(computerDTO.getDiscontinued(), actiualComputerDTO.getDiscontinued());
		assertEquals(computerDTO.getCompany().getId(), actiualComputerDTO.getCompany().getId());
		assertEquals(computerDTO.getCompany().getName(), actiualComputerDTO.getCompany().getName());
	}
}
