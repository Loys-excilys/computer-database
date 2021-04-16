package com.excilys.computer.database.mappeur;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.excilys.computer.database.builder.BuilderUser;
import com.excilys.computer.database.data.User;
import com.excilys.computer.database.dto.UserDatabaseDTO;
import com.excilys.computer.database.dto.UserStreamAddDTO;
import com.excilys.computer.database.dto.UserStreamDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.validator.ValidateurUser;

public class MapperUser {

	public UserDatabaseDTO userToUserDatabaseDTO(User user) {
		return new UserDatabaseDTO(user.getId(), user.getUsername(), BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()), user.getEnabled(),
				new MapperAuthorities().authoritiesToAuthoritiesDatabaseDTO(user.getAuthority()));
	}

	public User userDatabaseDTOToUser(UserDatabaseDTO userDTO) throws ErrorSaisieUser {
		return new ValidateurUser().valide(new BuilderUser().addId(userDTO.getId()).addUsername(userDTO.getUsername())
				.addPassword(userDTO.getPassword()).addEnabled(userDTO.getEnabled())
				.addAuthority(new MapperAuthorities().authoritiesDatabaseDTOToAuthorities(userDTO.getAuthority()))
				.build());
	}

	public UserStreamDTO userToUserStreamDTO(User user) {
		return new UserStreamDTO(user.getId(), user.getUsername(), user.getEnabled(),
				new MapperAuthorities().authoritiesToAuthoritiesStreamDTO(user.getAuthority()));
	}

	public User userStreamDTOToUser(UserStreamDTO userDTO) throws ErrorSaisieUser {
		return new ValidateurUser().valideStream(new BuilderUser().addId(userDTO.getId()).addUsername(userDTO.getUsername())
				.addEnabled(userDTO.getEnabled())
				.addAuthority(new MapperAuthorities().authoritiesStreamDTOToAuthorities(userDTO.getAuthority()))
				.build());
	}

	public User userStreamAddDTOToUser(UserStreamAddDTO userDTO) throws ErrorSaisieUser {
		return new ValidateurUser().valide(new BuilderUser().addUsername(userDTO.getUsername())
				.addPassword(userDTO.getPassword()).addEnabled(userDTO.getEnabled())
				.addAuthority(new MapperAuthorities().authoritiesStreamDTOToAuthorities(userDTO.getAuthority()))
				.build());
	}
}
