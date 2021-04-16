package com.excilys.computer.database.binding.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.excilys.computer.database.builder.BuilderAuthorities;
import com.excilys.computer.database.builder.BuilderUser;
import com.excilys.computer.database.data.Authorities;
import com.excilys.computer.database.data.User;
import com.excilys.computer.database.dto.AuthoritiesDatabaseDTO;
import com.excilys.computer.database.dto.AuthoritiesStreamDTO;
import com.excilys.computer.database.dto.UserDatabaseDTO;
import com.excilys.computer.database.dto.UserStreamAddDTO;
import com.excilys.computer.database.dto.UserStreamDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MapperUser;

public class TestMapperUser {

	private User userReference;
	private Authorities authoritiesReference;
	
	private UserDatabaseDTO userDatabaseDTOReference; 
	private AuthoritiesDatabaseDTO authoritiesDatabaseDTOReference;
	
	private UserStreamDTO userStreamDTOReference;
	private AuthoritiesStreamDTO authoritiesStreamDTOReference;
	
	private UserStreamAddDTO userStreamAddDTOReference;
	
	
	@BeforeEach
	public void setUp() {
		authoritiesReference = new BuilderAuthorities().addId(1).addAthority("ROLE_USER").build();
		userReference = new BuilderUser().addId(1).addUsername("admin").addPassword("admin").addAuthority(authoritiesReference).build();
		
		authoritiesDatabaseDTOReference = new AuthoritiesDatabaseDTO(1, "ROLE_USER");
		userDatabaseDTOReference = new UserDatabaseDTO(1, "admin", "admin", 1, authoritiesDatabaseDTOReference);
		
		authoritiesStreamDTOReference = new AuthoritiesStreamDTO(1, "ROLE_USER");
		userStreamDTOReference = new UserStreamDTO(1, "admin", 1, authoritiesStreamDTOReference);
		
		userStreamAddDTOReference = new UserStreamAddDTO("admin", "admin", 1, authoritiesStreamDTOReference);
	}
	
	@Test
	public void UserToUserDatabaseDTO() {
		UserDatabaseDTO userDTO = new MapperUser().userToUserDatabaseDTO(this.userReference);
		assertEquals(this.userDatabaseDTOReference.getId(), userDTO.getId());
		assertEquals(this.userDatabaseDTOReference.getUsername(), userDTO.getUsername());
		assertTrue(BCrypt.checkpw(this.userDatabaseDTOReference.getPassword(), userDTO.getPassword()));
		assertEquals(this.userDatabaseDTOReference.getAuthority().getId(), userDTO.getAuthority().getId());
		assertEquals(this.userDatabaseDTOReference.getAuthority().getAuthority(), userDTO.getAuthority().getAuthority());
	}
	
	@Test
	public void UserToUserStreamTO() {
		UserStreamDTO userDTO = new MapperUser().userToUserStreamDTO(this.userReference);
		assertEquals(this.userStreamDTOReference.getId(), userDTO.getId());
		assertEquals(this.userStreamDTOReference.getUsername(), userDTO.getUsername());
		assertEquals(this.userStreamDTOReference.getAuthority().getId(), userDTO.getAuthority().getId());
		assertEquals(this.userStreamDTOReference.getAuthority().getAuthority(), userDTO.getAuthority().getAuthority());
	}
	@Test
	public void UserDatabaseDTOToUser() {
		User user = null;
		try {
			user = new MapperUser().userDatabaseDTOToUser(this.userDatabaseDTOReference);
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
		}
		assertEquals(this.userReference.getId(), user.getId());
		assertEquals(this.userReference.getUsername(), user.getUsername());
		assertEquals(this.userReference.getPassword(), user.getPassword());
		assertEquals(this.userReference.getAuthority().getId(), user.getAuthority().getId());
		assertEquals(this.userReference.getAuthority().getAuthority(), user.getAuthority().getAuthority());
	}
	@Test
	public void UserStreamDTOToUser() {
		User user = null;
		try {
			user = new MapperUser().userStreamDTOToUser(this.userStreamDTOReference);
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
		}
		assertEquals(this.userReference.getId(), user.getId());
		assertEquals(this.userReference.getUsername(), user.getUsername());
		assertEquals(this.userReference.getAuthority().getId(), user.getAuthority().getId());
		assertEquals(this.userReference.getAuthority().getAuthority(), user.getAuthority().getAuthority());
	}
	@Test
	public void UserStreamAddDTOToUser() {
		User user = null;
		try {
			user = new MapperUser().userStreamAddDTOToUser(this.userStreamAddDTOReference);
		} catch (ErrorSaisieUser e) {
			e.formatEntry();
		}
		assertEquals(this.userReference.getUsername(), user.getUsername());
		assertEquals(this.userReference.getPassword(), user.getPassword());
		assertEquals(this.userReference.getAuthority().getId(), user.getAuthority().getId());
		assertEquals(this.userReference.getAuthority().getAuthority(), user.getAuthority().getAuthority());
	}
}
