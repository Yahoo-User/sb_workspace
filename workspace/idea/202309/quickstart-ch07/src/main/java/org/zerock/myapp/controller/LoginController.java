package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2

// Class contains `required fields`,
// you have to `force` NoArgsConstructor. (*****)
@NoArgsConstructor

@Controller
public class LoginController {
	
	
	@GetMapping("/login")
	String login() {
		log.trace("login() invoked.");
		
		return "login";
	} // login
	
	@GetMapping("/loginSuccess")
	void loginSuccess() {
		log.trace("loginSuccess() invoked.");
		
	} // loginSuccess
	
	
	@GetMapping("/accessDenied")
	void accessDenied() {
		log.trace("accessDenied() invoked.");
		
	} // accessDenied

} // end class
