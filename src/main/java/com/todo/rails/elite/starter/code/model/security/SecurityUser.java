package com.todo.rails.elite.starter.code.model.security;

import com.todo.rails.elite.starter.code.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class SecurityUser implements UserDetails {

	private final User user;

	public SecurityUser(User user) {
		this.user = user;
	}

	// TODO 6: update the SecurityUser model
	// Learner should implement mapping of roles to GrantedAuthority and any additional security logic
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//return null; // TODO: implement mapping from user.getRoles()
		return Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.toList();
	}

	@Override
	public String getPassword() {
		//return null; // TODO: return the user's password
		return user.getPassword();
	}

	@Override
	public String getUsername() {

		//return null; // TODO: return the user's username
		return user.getUsername();
	}

	// TODO: optionally implement other UserDetails methods if required
}
