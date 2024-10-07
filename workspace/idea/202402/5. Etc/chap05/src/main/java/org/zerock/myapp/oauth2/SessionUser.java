package org.zerock.myapp.oauth2;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.zerock.myapp.domain.User;

import java.io.Serializable;


@Log4j2
@ToString
@Getter
public class SessionUser implements Serializable {
    private final String name;
    private final String email;
    private final String picture;


    public SessionUser(User user) {
        log.trace("Constructor({}) invoked.", user);

        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    } // Constructor



} // end class
