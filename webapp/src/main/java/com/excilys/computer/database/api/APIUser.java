package com.excilys.computer.database.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computer.database.data.User;
import com.excilys.computer.database.dto.UserStreamDTO;
import com.excilys.computer.database.mappeur.MapperUser;
import com.excilys.computer.database.service.ServiceUser;

@RestController
@RequestMapping("/APIUser")
public class APIUser {
	
	@Autowired
	private ServiceUser serviceUser;
	
	@GetMapping(value = "/list", produces = "application/json")
	public ResponseEntity<List<UserStreamDTO>> getUserList(){
		List<User> listUser = this.serviceUser.getUserList();
		List<UserStreamDTO> listUserDTO = new ArrayList<>();
		for (User user : listUser) {
			listUserDTO.add(new MapperUser().userToUserStreamDTO(user));
		}
		return new ResponseEntity<>(listUserDTO, HttpStatus.OK);
	}
}
