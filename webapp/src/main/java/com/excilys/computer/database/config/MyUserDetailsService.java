package com.excilys.computer.database.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.excilys.computer.database.data.User;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.service.ServiceUser;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private ServiceUser serviceUser;

	@Override
	public UserDetails loadUserByUsername(String username) {

		try {
			User user = this.serviceUser.getUserByUsername(username);
			UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
		    builder.roles(user.getAuthority().getAuthority().substring(user.getAuthority().getAuthority().indexOf("_") + 1));
		    return builder.build();
		}catch(ErrorSaisieUser error) {
			error.formatEntry();
		}
		return null;
	}
}