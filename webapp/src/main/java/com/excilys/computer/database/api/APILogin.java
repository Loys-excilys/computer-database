package com.excilys.computer.database.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computer.database.dto.LoginDTO;
import com.excilys.computer.database.dto.TokenRefresh;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.service.ServiceUser;


@PropertySource("classpath:server.properties")
@RestController
@RequestMapping("/login")
public class APILogin {

	@Autowired
	private ServiceUser serviceUser;
	
	@Value("${url}")
	private String url;

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> login(@RequestBody LoginDTO login) {
		try {
			if (this.serviceUser.login(login.getUsername(), login.getPassword())) {
				return new ResponseEntity<>("authentification ok", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("authentification failed", HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
			return new ResponseEntity<>("authentification failed", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PostMapping(value = "/Oauth", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> Oauth(@RequestBody LoginDTO login) {
		StringBuilder builder = new StringBuilder();
		try {
			URL url = new URL("http://" + this.url + "/oauth/token?username=" + login.getUsername() + "&password="
					+ login.getPassword() + "&grant_type=password&client_id=clientIdPassword");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
	
			conn.setRequestProperty("Authorization", "Basic Y2xpZW50SWRQYXNzd29yZDpzZWNyZXQ=");
			conn.connect();
	
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				builder.append(line + "\n");
			}
		} catch (IOException error) {
			return new ResponseEntity<>("invalid login", HttpStatus.BAD_REQUEST);
		}
		String authority = null;
		try {
			authority = this.serviceUser.getUserByUsername(login.getUsername()).getAuthority().getAuthority();
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
		}
		return new ResponseEntity<>(builder.toString().replace("}", ",") + "\"role\":\"" + authority + "\"}", HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/Oauth/refresh", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> Oauth(@RequestBody TokenRefresh token) {
		StringBuilder builder = new StringBuilder();
		try {
			URL url = new URL("http://" + this.url + "/oauth/token?grant_type=refresh_token&refresh_token=" + token.getToken());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
	
			conn.setRequestProperty("Authorization", "Basic Y2xpZW50SWRQYXNzd29yZDpzZWNyZXQ=");
			conn.connect();
	
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				builder.append(line + "\n");
			}
		} catch (IOException error) {
			return new ResponseEntity<>(builder.toString(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(builder.toString(), HttpStatus.OK);
	}
}
