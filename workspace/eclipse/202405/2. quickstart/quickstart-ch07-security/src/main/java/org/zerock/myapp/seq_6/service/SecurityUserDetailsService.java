package org.zerock.myapp.seq_6.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.myapp.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_4.domain.Authorities;
import org.zerock.myapp.seq_4.domain.Users;
import org.zerock.myapp.seq_5.persistence.AuthoritiesRepository;
import org.zerock.myapp.seq_5.persistence.UsersRepository;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@Service("securityUserDetailsService")
public class SecurityUserDetailsService extends CommonBeanCallbacks implements UserDetailsService {
	@Autowired private UsersRepository usersRepo;
	@Autowired private AuthoritiesRepository authoritiesRepo;
	
	/**
	private final String usersQuery = "SELECT name as username, concat('{noop}', password) as password, true as enabled FROM member WHERE name = ?";
	private final String authoritiesQuery = "SELECT name as username, upper(role) as authority FROM member WHERE name = ?";
	  */
	
	@PostConstruct
	public void postConstruct() {
		log.trace("(2) {} -> postConstruct() invoked.", this.beanName);
		
		log.info("  + this.usersRepo: {}", this.usersRepo);
		log.info("  + this.authoritiesRepo: {}", this.authoritiesRepo);
	} // postConstruct

	
	/**
	 * 	(I) UserDetails		<--- implementation --- 	(C) User		<--- extends --- 		(C) SecurityUser
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.trace("loadUserByUsername({}) invoked.", username);

		// -------------
		Optional<Users> foundUser = this.usersRepo.findById(username);
		log.info("  + foundUser: {}", foundUser);

		// -------------
		List<Authorities> foundAuthorities = this.authoritiesRepo.findByUsername(username);
		List<String> authorities = foundAuthorities.stream().map(a -> "ROLE_" + a.getAuthority()).collect(Collectors.toList());
		
		log.info("  + foundAuthorities: {}", foundAuthorities);
		log.info("  + authorities: {}", authorities);

		// -------------
		SecurityUser securityUser = 
				new SecurityUser(
						username, 
						foundUser.orElse(null).getPassword(), 
						AuthorityUtils.createAuthorityList(authorities)
				);
		
		return securityUser;
	} // loadUserByUsername

} // end class

