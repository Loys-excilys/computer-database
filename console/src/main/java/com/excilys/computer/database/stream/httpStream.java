package com.excilys.computer.database.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.springframework.stereotype.Component;

import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.data.Page;
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
	
	String authHeaderValue = "Basic " + new String(Base64.getEncoder().encode("user:user".getBytes(StandardCharsets.UTF_8)));

	private String readAll(BufferedReader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

	private List<Computer> stringToComputer(String stringComputers) throws ErrorSaisieUser {
		if (stringComputers.charAt(0) != '[') {
			stringComputers = "[" + stringComputers + "]";
		}

		List<ComputerStreamDTO> listComputerDTO = null;
		List<Computer> listComputers = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		try {
			listComputerDTO = mapper.readValue(stringComputers, new TypeReference<List<ComputerStreamDTO>>() {
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		for (ComputerStreamDTO computerDTO : listComputerDTO) {
			listComputers.add(new MapperComputer().computerStreamDTOToComputer(computerDTO));
		}
		return listComputers;
	}

	private List<Company> stringToCompany(String stringCompanies) throws ErrorSaisieUser {
		if (stringCompanies.charAt(0) != '[') {
			stringCompanies = "[" + stringCompanies + "]";
		}
		List<CompanyStreamDTO> listCompanyDTO = null;
		List<Company> listCompanies = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		try {
			listCompanyDTO = mapper.readValue(stringCompanies, new TypeReference<List<CompanyStreamDTO>>() {
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		for (CompanyStreamDTO companyDTO : listCompanyDTO) {
			listCompanies.add(new MapperCompany().companyStreamDTOToCompany(companyDTO));
		}
		return listCompanies;
	}

	public Optional<Computer> getComputerStream(int commande) throws ErrorSaisieUser, IOException {
		URL serverUrl = new URL("http://localhost:8080/webapp/APIComputer/" + commande);
		HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();

		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestProperty("Method", "GET");
		connection.setRequestProperty("Authorization", this.authHeaderValue);
		InputStream is = connection.getInputStream();

		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			return Optional.ofNullable(this.stringToComputer(jsonText).get(0));
		} finally {
			is.close();
		}
	}
	
	public List<Computer> getComputerListStream(Page page) throws IOException, JSONException, ErrorSaisieUser {
		URL serverUrl = new URL("http://localhost:8080/webapp/APIComputer/page" + page.getPage() + "/" + page.getMaxPrint());
		HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();

		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestProperty("Method", "GET");
		connection.setRequestProperty("Authorization", this.authHeaderValue);
		InputStream is = connection.getInputStream();

		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			return this.stringToComputer(jsonText);
		} finally {
			is.close();
		}
	}

	public Optional<Company> getCompanyStream(String name) throws ErrorSaisieUser, IOException {
		URL serverUrl = new URL("http://localhost:8080/webapp/APICompany/" + name);
		HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();

		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestProperty("Method", "GET");
		connection.setRequestProperty("Authorization", this.authHeaderValue);
		InputStream is = connection.getInputStream();

		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			return Optional.ofNullable(this.stringToCompany(jsonText).get(0));
		} finally {
			is.close();
		}
	}
	
	public List<Company> getCompanyListStream(int page) throws IOException, JSONException, ErrorSaisieUser {
		URL serverUrl = new URL("http://localhost:8080/webapp/APICompany/page/" + page);
		HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();

		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestProperty("Method", "GET");
		connection.setRequestProperty("Authorization", this.authHeaderValue);
		InputStream is = connection.getInputStream();

		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			return this.stringToCompany(jsonText);
		} finally {
			is.close();
		}
	}

	public void addComputerStream(Computer computer) throws IOException, JSONException, ErrorSaisieUser {
		URL serverUrl = new URL("http://localhost:8080/webapp/APIComputer/add");
		HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();

		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Method", "POST");
		connection.setRequestProperty("Authorization", this.authHeaderValue);

		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		writer.write(new MapperComputer().computerToComputerStreamDTO(computer).toJson());
		writer.close();

		System.out.println(connection.getResponseCode());

	}

	public void updateComputerStream(Computer computer) throws IOException {
		URL serverUrl = new URL("http://localhost:8080/webapp/APIComputer/update");
		HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();

		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Method", "POST");
		connection.setRequestProperty("Authorization", this.authHeaderValue);

		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		writer.write(new MapperComputer().computerToComputerStreamDTO(computer).toJson());
		writer.close();

		System.out.println(connection.getResponseCode());
	}

	public void deleteComputerById(int idComputerSelected) throws IOException {
		URL serverUrl = new URL("http://localhost:8080/webapp/APIComputer/delete?id=" + idComputerSelected);
		HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();

		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestProperty("Method", "DELETE");
		connection.setRequestProperty("Authorization", this.authHeaderValue);

		System.out.println(connection.getResponseCode());
	}

	public void deleteCompanyById(int idCompanySelected) throws IOException {
		URL serverUrl = new URL("http://localhost:8080/webapp/APICompany/delete?id=" + idCompanySelected);
		HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();

		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestProperty("Method", "DELETE");
		connection.setRequestProperty("Authorization", this.authHeaderValue);

		System.out.println(connection.getResponseCode());
	}
}
