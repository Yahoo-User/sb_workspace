package org.zerock.myapp.config;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.persistence.MemberRepository;

import java.util.Optional;


@Log4j2
@NoArgsConstructor

@Service
public class BoardUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.trace("loadUserByUsername({}) invoked.", username);

        Optional<Member> foundMember = this.memberRepo.findById(username);

        if(!foundMember.isPresent()) {  // No found.
            throw new UsernameNotFoundException("No " + username + " found.");
        } else {    // Found
            Member member = foundMember.get();

            return new SecurityUser(member);
        } // if-else
    } // loadUserByUsername

} // end class
