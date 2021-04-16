package com.excilys.computer.database.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.data.User;
import com.excilys.computer.database.dto.UserStreamAddDTO;
import com.excilys.computer.database.dto.UserStreamDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperUser;
import com.excilys.computer.database.service.ServiceUser;

@RestController
@RequestMapping("/APIUser")
public class APIUser {
	
	@Autowired
	private ServiceUser serviceUser;
	
	@GetMapping(value = "/list", produces = "application/json")
	public ResponseEntity<List<UserStreamDTO>> getList(){
		List<User> listUser = this.serviceUser.getUserList();
		List<UserStreamDTO> listUserDTO = new ArrayList<>();
		for (User user : listUser) {
			listUserDTO.add(new MapperUser().userToUserStreamDTO(user));
		}
		return new ResponseEntity<>(listUserDTO, HttpStatus.OK);
	}
	
	
	@GetMapping(value ="/list/page/{numPage}/{maxEntry}")
	public ResponseEntity<List<UserStreamDTO>> getList(@PathVariable int numPage, @PathVariable int maxEntry){
		Page page = new Page();
		page.setPage(numPage);
		page.setMaxPrint(maxEntry);
		
		
		List<User> listUser = this.serviceUser.getUserList(page);
		List<UserStreamDTO> listUserDTO = new ArrayList<>();
		for (User user : listUser) {
			listUserDTO.add(new MapperUser().userToUserStreamDTO(user));
		}
		return new ResponseEntity<>(listUserDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> Add(@RequestBody UserStreamAddDTO userDTO) {
		try {
			this.serviceUser.addUser(new MapperUser().userStreamAddDTOToUser(userDTO));
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
			return new ResponseEntity<>("error ajout, verifié les données envoyées", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Ajout effectuer", HttpStatus.OK);
	}

	@PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> Update(@RequestBody UserStreamDTO userDTO) {
		try {
			this.serviceUser.updateUser(new MapperUser().userStreamDTOToUser(userDTO));
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
			return new ResponseEntity<>("error update, verifié les données envoyées", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("update effectuer", HttpStatus.OK);
	}
	
	@PutMapping(value = "/resetpassword", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> ResetPassword(@RequestBody UserStreamDTO userDTO) {
		try {
			User user = new MapperUser().userStreamDTOToUser(userDTO);
			user.setPassword("excilys");
			this.serviceUser.updateUser(user);
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
			return new ResponseEntity<>("error reset, verifié les données envoyées", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("reset effectuer", HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete", produces = "application/json")
	public ResponseEntity<String> Delete(@RequestParam int id) {
		try {
			this.serviceUser.deleteUser(id);
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
			return new ResponseEntity<>("error delete, verifié les données envoyées", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("delete effectuer", HttpStatus.OK);
	}
}
