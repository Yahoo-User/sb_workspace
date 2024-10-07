package org.zerock.myapp.controller;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.bean.RequestScopeBean;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RestController("requestController")
public class RequestController {
	@Autowired private RequestScopeBean requestScopeBean;
	
	
	@GetMapping("/set-requestScopeBean")
	Object setRequestScopeBean() {
		log.trace("setRequestScopeBean() invoked.");
		
		Objects.requireNonNull(this.requestScopeBean);
		this.requestScopeBean.setContainer(Arrays.<Integer>asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
		
		return this.requestScopeBean.getContainer();
	} // setRequestScopeBean
	
	@GetMapping("/get-requestScopeBean")
	String getRequestScopeBean() {
		log.trace("getRequestScopeBean() invoked.");

		Objects.requireNonNull(this.requestScopeBean);
		return "1. Got The Request Scoped Bean.<br>2. requestScopeBean: %s<br>3. container: %s"
					.formatted(this.requestScopeBean, this.requestScopeBean.getContainer());
	} // getRequestScopeBean

} // end class
