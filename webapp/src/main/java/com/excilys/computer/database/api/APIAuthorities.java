package com.excilys.computer.database.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computer.database.data.Authorities;
import com.excilys.computer.database.dto.AuthoritiesStreamDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperAuthorities;
import com.excilys.computer.database.service.ServiceAuthorities;


@RestController
@RequestMapping("/APIAuthorities")
public class APIAuthorities {
	
	@Autowired
	private ServiceAuthorities serviceAuthorities;
	
	@GetMapping(value = "/list", produces = "application/json")
	public ResponseEntity<List<AuthoritiesStreamDTO>> getAuthoritiesList(){
		List<Authorities> listAuthorities = this.serviceAuthorities.getAuthoritiesList();
		List<AuthoritiesStreamDTO> listAuthoritiesDTO = new ArrayList<>();
		for (Authorities authorities : listAuthorities) {
			listAuthoritiesDTO.add(new MapperAuthorities().authoritiesToAuthoritiesStreamDTO(authorities));
		}
		return new ResponseEntity<>(listAuthoritiesDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> AddAuthorities(@RequestBody AuthoritiesStreamDTO AuthoritiesDTO) {
		try {
			this.serviceAuthorities.addAuthorities(new MapperAuthorities().authoritiesStreamDTOToAuthorities(AuthoritiesDTO));
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
			return new ResponseEntity<>("error ajout, verifié les données envoyées", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Ajout effectuer", HttpStatus.OK);
	}

	@PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> UpdateAuthorities(@RequestBody AuthoritiesStreamDTO AuthoritiesDTO) {
		try {
			this.serviceAuthorities.updateAuthorities(new MapperAuthorities().authoritiesStreamDTOToAuthorities(AuthoritiesDTO));
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
			return new ResponseEntity<>("error update, verifié les données envoyées", HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<>("update effectuer", HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete", produces = "application/json")
	public ResponseEntity<String> UpdateAuthorities(@RequestParam int id) {
		try {
			this.serviceAuthorities.deleteAuthorities(id);
		} catch (ErrorSaisieUser e) {
			e.printStackTrace();
			return new ResponseEntity<>("error delete, verifié les données envoyées", HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<>("delete effectuer", HttpStatus.OK);
	}

}
