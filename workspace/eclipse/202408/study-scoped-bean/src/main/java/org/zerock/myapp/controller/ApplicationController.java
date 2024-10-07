package org.zerock.myapp.controller;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.bean.ApplicationScopeBean;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RestController("applicationController")
public class ApplicationController {
	@Autowired private ApplicationScopeBean applicationScopeBean;
	
	
	@GetMapping("/set-applicationScopeBean")
	Object setApplicationScopeBean() {
		log.trace("setApplicationScopeBean() invoked.");

		Objects.requireNonNull(this.applicationScopeBean);
		this.applicationScopeBean.setContainer(Map.<Integer, String>of(1, "NAME1", 2, "NAME2", 3, "NAME3"));
		
		return this.applicationScopeBean.getContainer();
	} // setApplicationScopeBean
	
	@GetMapping("/get-applicationScopeBean")
	String getApplicationScopeBean() {
		log.trace("getApplicationScopeBean() invoked.");
		
		Objects.requireNonNull(this.applicationScopeBean);
		return "1. Got The Application Scoped Bean.<br>2. applicationScopeBean: %s<br>3. container: %s"
					.formatted(this.applicationScopeBean, this.applicationScopeBean.getContainer());
	} // getApplicationScopeBean

} // end class
