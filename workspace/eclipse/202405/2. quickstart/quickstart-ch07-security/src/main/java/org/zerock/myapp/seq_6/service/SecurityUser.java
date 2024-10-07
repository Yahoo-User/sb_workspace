package org.zerock.myapp.seq_6.service;

import java.io.Serial;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class SecurityUser extends User {
	@Serial private static final long serialVersionUID = 1L;


	public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
		log.trace("SecurityUser({}, {}, {}) invoked.", username, password, authorities);
	} // Constructor

} // end class
