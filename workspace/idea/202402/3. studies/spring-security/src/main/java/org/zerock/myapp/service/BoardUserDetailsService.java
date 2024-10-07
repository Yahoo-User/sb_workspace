package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.myapp.config.SecurityUser;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.persistence.MemberRepository;

import java.util.Objects;
import java.util.Optional;


@Log4j2
@NoArgsConstructor

@Service
public class BoardUserDetailsService implements UserDetailsService {
    @Setter(onMethod_ = @Autowired)
    private MemberRepository memberRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.trace("loadUserByUsername({}) invoked.", username);

        log.info("\t+ this.memberRepo: {}", memberRepo);
        Objects.requireNonNull(this.memberRepo);

        Optional<Member> optional = this.memberRepo.findById(username);
        if(optional.isPresent()) {
            Member member = optional.get();
            log.info("\t+ member: {}", member);

            return new SecurityUser(member);
        } else {
            throw new UsernameNotFoundException( String.format("No %s username found.", username) );
        } // if-else
    } // loadUserByUsername

} // end class
