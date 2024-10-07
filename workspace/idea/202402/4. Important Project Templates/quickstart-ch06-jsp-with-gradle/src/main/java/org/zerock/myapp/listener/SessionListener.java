package org.zerock.myapp.listener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

//=================================================
// 1. For Spring Boot 2.7.x
//=================================================
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//=================================================
// 2. For Spring Boot 3.1.x
//=================================================
//import jakarta.servlet.annotation.WebListener;
//import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.HttpSessionEvent;
//import jakarta.servlet.http.HttpSessionListener;


@Log4j2
@NoArgsConstructor

@WebListener
public class SessionListener implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.trace("---------------------------------------");
        log.trace("* sessionCreated(se) invoked.");
        log.trace("---------------------------------------");

        HttpSession httpSession = se.getSession();
        log.info("\t+ session: {}", httpSession.getId());
    } // sessionCreated

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.trace("---------------------------------------");
        log.trace("* sessionDestroyed(se) invoked.");
        log.trace("---------------------------------------");

        HttpSession httpSession = se.getSession();
        log.info("\t+ session: {}", httpSession.getId());
    } // sessionDestroyed

} // end class
