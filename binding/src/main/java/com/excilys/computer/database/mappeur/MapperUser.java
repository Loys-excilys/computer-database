package com.excilys.computer.database.mappeur;

import com.excilys.computer.database.builder.BuilderUser;
import com.excilys.computer.database.data.User;
import com.excilys.computer.database.dto.UserDatabaseDTO;
import com.excilys.computer.database.dto.UserStreamDTO;

public class MapperUser {

	public UserDatabaseDTO userToUserDatabaseDTO(User user) {
		return new UserDatabaseDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEnabled(),
				new MapperAuthorities().authoritiesToAuthoritiesDatabaseDTO(user.getAuthority()));
	}

	public User userDatabaseDTOToUser(UserDatabaseDTO userDTO) {
		return new BuilderUser().addId(userDTO.getId()).addUsername(userDTO.getUsername())
				.addPassword(userDTO.getPassword()).addEnabled(userDTO.getEnabled())
				.addAuthority(new MapperAuthorities().authoritiesDatabaseDTOToAuthorities(userDTO.getAuthority()))
				.build();
	}

	public UserStreamDTO userToUserStreamDTO(User user) {
		return new UserStreamDTO(user.getId(), user.getUsername(), user.getEnabled(),
				new MapperAuthorities().authoritiesToAuthoritiesStreamDTO(user.getAuthority()));
	}

	public User userStreamDTOToUser(UserStreamDTO userDTO) {
		return new BuilderUser().addId(userDTO.getId()).addUsername(userDTO.getUsername())
				.addEnabled(userDTO.getEnabled())
				.addAuthority(new MapperAuthorities().authoritiesStreamDTOToAuthorities(userDTO.getAuthority()))
				.build();
	}
}
