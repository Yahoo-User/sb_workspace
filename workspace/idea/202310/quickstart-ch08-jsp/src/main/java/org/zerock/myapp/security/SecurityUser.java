package org.zerock.myapp.security;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.zerock.myapp.domain.Member;


@Log4j2

@Getter
@ToString
public class SecurityUser extends User {
    private final Member member;

    //-- The below two constructors are defined in the parent `User` class. --//
//    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//    } // Constructor #1

//    public User(String username, String password, boolean enabled, boolean accountNonExpired,
//                boolean credentialsNonExpired, boolean accountNonLocked,
//                Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//    } // Constructor #2

    public SecurityUser(Member member) {
        // 1. Before adding `PasswordEncoder` Bean.
//        super(member.getId(), "{noop}"+member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().name()));

        // 2. After adding `PasswordEncoder` Bean, automatically member password should be encoded by `PasswordEncoder` Bean.
        super(member.getId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().name()));

        log.trace("SecurityUser({}) invoked.", member);
        this.member = member;
    } // Constructor

} // end class
