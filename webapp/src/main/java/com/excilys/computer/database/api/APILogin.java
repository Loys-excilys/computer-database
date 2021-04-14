package com.excilys.computer.database.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computer.database.dto.LoginDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.service.ServiceUser;

@RestController
@RequestMapping("/login")
public class APILogin {
	
	@Autowired
	private ServiceUser serviceUser;

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> loginPage(@RequestBody LoginDTO login) {
		
		try {
			if(this.serviceUser.login(login.getUsername(), login.getPassword())) {
				return new ResponseEntity<>("authentification ok", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("authentification failed", HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
			return new ResponseEntity<>("authentification failed", HttpStatus.NOT_ACCEPTABLE);
		}		
	}
}
