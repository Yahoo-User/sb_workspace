package org.zerock.myapp.security;

import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.myapp.dao.MemberRepository;
import org.zerock.myapp.domain.Member;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service
public class CustomUserDetailsService
	implements UserDetailsService, InitializingBean {
	
	@Autowired
	private MemberRepository memberDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("loadUserByUsername({}) invoked.", username);
		
		Optional<Member> optional = this.memberDao.findById(username);
		log.info("\t+ optional: {}", optional);
		
		if(optional.isPresent()) {
			CustomUser user = new CustomUser(optional.get());
			log.info("\t+ user: {}", user);
			
			return user;
		} else {
			throw new UsernameNotFoundException("No User `"+ username + "` Found.");
		} // if-else
	} // loadUserByUsername


//	==================================
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
		
		assert this.memberDao != null;
		log.info("\t+ memberDao: {}, type: {}", this.memberDao, this.memberDao.getClass().getName());
	} // afterPropertiesSet
	
} // end class
