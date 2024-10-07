package org.zerock.myapp.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.zerock.myapp.domain.Member;


@Log4j2
public class SecurityUser extends User {


    // super(String username, String password, Collection<? extends GrantedAuthority> authorities);
    public SecurityUser(Member member) {
        super(
            member.getId(),
//            "{noop}" + member.getPassword(),
            member.getPassword(),
            AuthorityUtils.createAuthorityList(member.getRole().toString())
        );

        log.trace("SecurityUser({}, {}, {}) invoked.",
                member.getId(),
//                "{noop}" + member.getPassword(),
                member.getPassword(),
                AuthorityUtils.createAuthorityList(member.getRole().toString())
        );
    } // Constructor

} // end class
