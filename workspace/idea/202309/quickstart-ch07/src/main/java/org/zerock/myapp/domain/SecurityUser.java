package org.zerock.myapp.domain;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.zerock.myapp.entity.Member;


@Log4j2
public class SecurityUser extends User {


    public SecurityUser(Member member) {
        super(
                member.getId(),

                // Before encoding password in the `member` table.
                //"{noop}" + member.getPassword(),

                // After encoding password in the `member` table.
                member.getPassword(),

                AuthorityUtils.createAuthorityList(member.getRole().name())
        );

        log.trace("Constructor({}) invoked.", member);
        log.info("\t+ authorities: {}", AuthorityUtils.createAuthorityList(member.getRole().name()));
    } // Constructor

} // end class
