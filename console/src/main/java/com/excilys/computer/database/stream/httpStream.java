package com.excilys.computer.database.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.springframework.stereotype.Component;

import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.dto.CompanyStreamDTO;
import com.excilys.computer.database.dto.ComputerStreamDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperCompany;
import com.excilys.computer.database.mappeur.MapperComputer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class httpStream {

	private String readAll(BufferedReader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

	private List<Computer> stringToComputer(String stringComputers) throws ErrorSaisieUser{
		List<ComputerStreamDTO> listComputerDTO = null;
		List<Computer> listComputers = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		try {
			listComputerDTO = mapper.readValue(stringComputers, new TypeReference<List<ComputerStreamDTO>>(){});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		for(ComputerStreamDTO computerDTO : listComputerDTO) {
			listComputers.add(new MapperComputer().computerStreamDTOToComputer(computerDTO));
		}
		return listComputers;
	}
	
	private List<Company> stringToCompany(String stringCompanies) throws ErrorSaisieUser{
		List<CompanyStreamDTO> listCompanyDTO = null;
		List<Company> listCompanies = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		try {
			listCompanyDTO = mapper.readValue(stringCompanies, new TypeReference<List<CompanyStreamDTO>>(){});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		for(CompanyStreamDTO companyDTO : listCompanyDTO) {
			listCompanies.add(new MapperCompany().companyStreamDTOToCompany(companyDTO));
		}
		return listCompanies;
	}

	public List<Computer> ComputerListStream(String url) throws IOException, JSONException, ErrorSaisieUser {
		String serverUrl = "http://localhost:8080/webapp/APIComputer/";
		String access = "--user user:user ";
		Runtime runtime = Runtime.getRuntime();
		InputStream is = runtime.exec("curl " + access + serverUrl + url).getInputStream();

		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			return this.stringToComputer(jsonText);
		} finally {
			is.close();
		}
	}
	
	public List<Company> CompanyListStream(String url) throws IOException, JSONException, ErrorSaisieUser {
		String serverUrl = "http://localhost:8080/webapp/APICompany/";
		String access = "--user user:user ";
		Runtime runtime = Runtime.getRuntime();
		InputStream is = runtime.exec("curl " + access + serverUrl + url).getInputStream();

		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			return this.stringToCompany(jsonText);
		} finally {
			is.close();
		}
	}
}
