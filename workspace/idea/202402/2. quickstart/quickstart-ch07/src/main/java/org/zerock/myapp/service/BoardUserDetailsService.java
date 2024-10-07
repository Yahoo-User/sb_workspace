package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.SecurityUser;
import org.zerock.myapp.entity.Member;
import org.zerock.myapp.persistence.MemberRepository;

import java.util.Optional;


@Log4j2

// Class contains `required fields`, You have to `force` NoArgsConstructor. (***)
@NoArgsConstructor


@Service
public class BoardUserDetailsService implements UserDetailsService, InitializingBean {
    @Autowired
    private MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.trace("loadUserByUsername({}) invoked.", username);

        // Step.1 Search User By `MemberRepository`.
        Optional<Member> memberOptional = this.memberRepository.findById(username);
        log.info("\t+ memberOptional: {}", memberOptional);

        // Step.2 Returns The Found User AS a `UserDetails (Interface)` Type.
        return new SecurityUser(
            memberOptional.orElseThrow(() -> new UsernameNotFoundException("No user found. username = " + username))
        );
    } // loadUserByUsername


    @Override
    public void afterPropertiesSet() {
        log.trace("afterPropertiesSet() invoked.");
        log.info("\t+ this.memberRepository: {}", this.memberRepository);
    } // afterPropertiesSet

} // end class
