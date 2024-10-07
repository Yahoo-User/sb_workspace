package org.zerock.myapp.security;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.persistence.MemberRepository;

import java.util.Objects;


@Log4j2

@ToString
@NoArgsConstructor

// ** Required ** annotation.
@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Setter(onMethod_ = @Autowired)
    private MemberRepository memberRepository;


    /*
     * ===========================================================
     * 'UserDetailsService.loadUserByUsername(username)` method.
     * ===========================================================
     * Locates the user based on the username.
     * In the actual implementation, the search may possibly be case sensitive,
     * or case insensitive depending on how the implementation instance is configured.
     *
     * In this case, the `UserDetails` object that comes back may have a username
     * that is of a different case than what was actually requested.
     *
     * @param username the username identifying the user whose data is required.
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.trace("loadUserByUsername({}) invoked.", username);

        try {
            Objects.requireNonNull(this.memberRepository);

            Member member = this.memberRepository.findById(username).orElseThrow();
            Objects.requireNonNull(member);

            return new SecurityUser(member);
        } catch(Exception e) {
            throw new UsernameNotFoundException("No `"+username+"` Not Found in the `Member Repository`.");
        } // try-catch
    } // loadUserByUsername

} // end class
