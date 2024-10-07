package org.zerock.myapp.listener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


//=================================================
// 1. For Spring Boot 2.7.x
//=================================================
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.annotation.WebListener;

//=================================================
// 2. For Spring Boot 3.1.x
//=================================================
//import jakarta.servlet.http.HttpSessionEvent;
//import jakarta.servlet.http.HttpSessionIdListener;
//import jakarta.servlet.annotation.WebListener;


@Log4j2

// Class contains `required fields`, You have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@WebListener
public class SessionIdListener implements HttpSessionIdListener {


    @Override
    public void sessionIdChanged(HttpSessionEvent se, String oldSessionId) {
        log.trace("---------------------------------------");
        log.trace("* sessionIdChanged(se, oldSessionId: {}) invoked.", oldSessionId);
        log.trace("---------------------------------------");

        log.info("\t+ oldSession: {} -> newSession: {}", oldSessionId, se.getSession().getId());
    } // sessionIdChanged

} // end class
