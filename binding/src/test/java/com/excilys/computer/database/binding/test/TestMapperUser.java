package com.excilys.computer.database.binding.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.excilys.computer.database.builder.BuilderAuthorities;
import com.excilys.computer.database.builder.BuilderUser;
import com.excilys.computer.database.data.Authorities;
import com.excilys.computer.database.data.User;
import com.excilys.computer.database.dto.AuthoritiesDatabaseDTO;
import com.excilys.computer.database.dto.AuthoritiesStreamDTO;
import com.excilys.computer.database.dto.UserDatabaseDTO;
import com.excilys.computer.database.dto.UserStreamDTO;
import com.excilys.computer.database.mappeur.MapperUser;

public class TestMapperUser {

	private User userReference;
	private Authorities authoritiesReference;
	
	private UserDatabaseDTO UserDatabaseDTOReference; 
	private AuthoritiesDatabaseDTO AuthoritiesDatabaseDTOReference;
	
	private UserStreamDTO UserStreamDTOReference;
	private AuthoritiesStreamDTO AuthoritiesStreamDTOReference;
	
	
	@BeforeEach
	public void setUp() {
		authoritiesReference = new BuilderAuthorities().addId(1).addAthority("ROLE_USER").build();
		userReference = new BuilderUser().addId(1).addUsername("admin").addPassword("admin").addAuthority(authoritiesReference).build();
		
		AuthoritiesDatabaseDTOReference = new AuthoritiesDatabaseDTO(1, "ROLE_USER");
		UserDatabaseDTOReference = new UserDatabaseDTO(1, "admin", "admin", 1, AuthoritiesDatabaseDTOReference);
		
		AuthoritiesStreamDTOReference = new AuthoritiesStreamDTO(1, "ROLE_USER");
		UserStreamDTOReference = new UserStreamDTO(1, "admin", 1, AuthoritiesStreamDTOReference);
		
	}
	
	@Test
	public void UserToUserDatabaseDTO() {
		UserDatabaseDTO userDTO = new MapperUser().userToUserDatabaseDTO(this.userReference);
		//assertEquals(userDTO);
	}
}
