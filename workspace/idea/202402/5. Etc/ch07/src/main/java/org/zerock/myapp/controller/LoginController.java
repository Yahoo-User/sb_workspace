package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Controller
public class LoginController {
	
	
	@GetMapping("/authLogin")
	String authLogin() {
		log.trace("authLogin() invoked.");
		
		return "authLogin";
	} // login
	
	@GetMapping("/authLoginSuccess")
	void authLoginSuccess() {
		log.trace("authLoginSuccess() invoked.");
		
	} // authLoginSuccess
	
	
	@GetMapping("/accessDenied")
	void accessDenied() {
		log.trace("accessDenied() invoked.");
		
	} // accessDenied

} // end class
