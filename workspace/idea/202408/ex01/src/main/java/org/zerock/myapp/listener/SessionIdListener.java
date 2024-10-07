package org.zerock.myapp.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionIdListener;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2

// Class contains `required fields`,
// you have to `force` NoArgsConstructor. (***)
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
