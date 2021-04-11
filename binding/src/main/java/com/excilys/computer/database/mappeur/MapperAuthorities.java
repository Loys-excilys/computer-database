package com.excilys.computer.database.mappeur;

import com.excilys.computer.database.builder.BuilderAuthorities;
import com.excilys.computer.database.data.Authorities;
import com.excilys.computer.database.dto.AuthoritiesDatabaseDTO;
import com.excilys.computer.database.dto.AuthoritiesStreamDTO;

public class MapperAuthorities {

	public Authorities authoritiesDatabaseDTOToAuthorities(AuthoritiesDatabaseDTO authoritiesDTO) {
		return new BuilderAuthorities()
				.addId(authoritiesDTO.getId())
				.addAthority(authoritiesDTO.getAuthority()).build();
	}

	public AuthoritiesDatabaseDTO authoritiesToAuthoritiesDatabaseDTO(Authorities authorities) {
		return new AuthoritiesDatabaseDTO(authorities.getId(),
				authorities.getAuthority());
	}

	public Authorities authoritiesStreamDTOToauthorities(AuthoritiesStreamDTO authoritiesDTO) {
		return new BuilderAuthorities().addId(authoritiesDTO.getId())
				.addAthority(authoritiesDTO.getAuthority()).build();
	}

	public AuthoritiesStreamDTO authoritiesToAuthoritiesStreamDTO(Authorities authorities) {
		return new AuthoritiesStreamDTO(authorities.getId(),
				authorities.getAuthority());
	}
}
