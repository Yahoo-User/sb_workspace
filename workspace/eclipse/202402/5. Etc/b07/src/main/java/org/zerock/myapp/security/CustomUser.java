package org.zerock.myapp.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.zerock.myapp.domain.Member;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class CustomUser extends User {
	private static final long serialVersionUID = 6323680218048876935L;


	public CustomUser(Member member) {
		super(
			member.getId(),														// username
//			"{noop}"+member.getPassword(),										// password (***)
			member.getPassword(),												// crypted password
			AuthorityUtils.createAuthorityList(member.getRole().toString())		// authorities
		); // super
		
		log.debug("Constructor({}) invoked.", member);
	} // Constructor

} // end class
