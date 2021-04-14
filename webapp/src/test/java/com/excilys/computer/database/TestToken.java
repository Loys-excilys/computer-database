package com.excilys.computer.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.jupiter.api.Test;
public class TestToken {

	@Test
	public void ComputerFormAddDTO() throws IOException {
		URL url = new URL("http://localhost:8080/webapp/oauth/token?username=admin&password=admin&grant_type=password&client_id=clientIdPassword");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.addRequestProperty("content-type", "application/json");
		conn.setDoOutput(true);
		
		conn.setRequestProperty("Authorization", "Basic Y2xpZW50SWRQYXNzd29yZDpzZWNyZXQ=");
		conn.connect();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		 
	    StringBuilder builder = new StringBuilder();
	    for(String line = reader.readLine(); line != null; line = reader.readLine()) {
	      builder.append(line + "\n");
	    }
	    
	    System.out.println(builder.toString());

	}
}
