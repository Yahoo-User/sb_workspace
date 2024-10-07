package org.zerock.myapp.controller;

import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.bean.SessionScopeBean;

import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RestController("sessionController")
public class SessionController {
	@Autowired private SessionScopeBean sessionScopeBean;
	
	
	@GetMapping("/set-sessionScopeBean")
	Object setSessionScopeBean() {
		log.trace("setSessionScopeBean() invoked.");
		
		Objects.requireNonNull(this.sessionScopeBean);
		this.sessionScopeBean.setContainer(Set.<Double>of(1., 2., 3., 4., 5., 6., 7.));
		
		return this.sessionScopeBean.getContainer();
	} // setSessionScopeBean
	
	@GetMapping("/get-sessionScopeBean")
	String getSessionScopeBean() {
		log.trace("getSessionScopeBean() invoked.");

		Objects.requireNonNull(this.sessionScopeBean);
		return "1. Got The Session Scoped Bean.<br>2. sessionScopeBean: %s<br>3. container: %s"
					.formatted(this.sessionScopeBean, this.sessionScopeBean.getContainer());
	} // getSessionScopeBean
	
	@GetMapping("/destroy-session")
	String destroySession(HttpSession session) {
		log.trace("destroySession({}) invoked.", session.getId());
		
		session.invalidate();
		return "Destroyed Session (%s)".formatted(session.getId());
	} // destroySession
} // end class
