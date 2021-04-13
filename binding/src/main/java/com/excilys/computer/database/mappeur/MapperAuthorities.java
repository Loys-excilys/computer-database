package com.excilys.computer.database.mappeur;

import com.excilys.computer.database.builder.BuilderAuthorities;
import com.excilys.computer.database.data.Authorities;
import com.excilys.computer.database.dto.AuthoritiesDatabaseDTO;
import com.excilys.computer.database.dto.AuthoritiesStreamDTO;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.validator.ValidateurAuthorities;

public class MapperAuthorities {

	public Authorities authoritiesDatabaseDTOToAuthorities(AuthoritiesDatabaseDTO authoritiesDTO) throws ErrorSaisieUser {
		return new ValidateurAuthorities().valide(new BuilderAuthorities()
				.addId(authoritiesDTO.getId())
				.addAthority(authoritiesDTO.getAuthority()).build());
	}

	public AuthoritiesDatabaseDTO authoritiesToAuthoritiesDatabaseDTO(Authorities authorities) {
		return new AuthoritiesDatabaseDTO(authorities.getId(),
				authorities.getAuthority());
	}

	public Authorities authoritiesStreamDTOToAuthorities(AuthoritiesStreamDTO authoritiesDTO) throws ErrorSaisieUser {
		return new ValidateurAuthorities().valide(new BuilderAuthorities().addId(authoritiesDTO.getId())
				.addAthority(authoritiesDTO.getAuthority()).build());
	}

	public AuthoritiesStreamDTO authoritiesToAuthoritiesStreamDTO(Authorities authorities) {
		return new AuthoritiesStreamDTO(authorities.getId(),
				authorities.getAuthority());
	}
}
