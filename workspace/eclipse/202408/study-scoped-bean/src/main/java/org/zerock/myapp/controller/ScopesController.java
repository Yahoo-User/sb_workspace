package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.bean.ApplicationScopeBean;
import org.zerock.myapp.bean.RequestScopeBean;
import org.zerock.myapp.bean.SessionScopeBean;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RestController("scopesController")
public class ScopesController {
	@Autowired private ApplicationScopeBean applicationScopeBean;
	@Autowired private SessionScopeBean sessionScopeBean;
	@Autowired private RequestScopeBean requestScopeBean;
	
	
	@GetMapping("/scopes")
	String scopes() {
		log.trace("scopes() invoked.");
		
		return "1. applicationScopeBean: %s<br>2. sessionScopeBean: %s<br>3. requestScopeBean: %s"
					.formatted(this.applicationScopeBean, this.sessionScopeBean, this.requestScopeBean);
	} // scopes

} // end class
