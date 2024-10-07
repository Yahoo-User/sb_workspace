package org.zerock.myapp.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.zerock.myapp.domain.Member;


@Log4j2
public class SecurityUser extends User {
    private static final double serialVersionUID = 1L;


    public SecurityUser(Member member) {
        super(
            member.getId(),
//            "{noop}"+member.getPassword(),
            member.getPassword(),
            AuthorityUtils.createAuthorityList( member.getRole().name() )
        );

        log.info("SecurityUser({}) invoked.", member);
    } // Constructor

} // end class
