package org.zerock.myapp.listener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

//=================================================
// 1. For Spring Boot 2.7.x
//=================================================
//import javax.servlet.annotation.WebListener;
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionIdListener;

//=================================================
// 2. For Spring Boot 3.1.x
//=================================================
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionIdListener;


@Log4j2
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
