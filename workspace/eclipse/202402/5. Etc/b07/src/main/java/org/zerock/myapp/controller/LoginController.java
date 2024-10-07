package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Controller
public class LoginController {
	
	
	@GetMapping("/login")
	void loginView() {
		log.debug("loginView() invoked.");
		
	} // loginView
	
	@GetMapping("/loginSuccess")
	void loginSuccessView() {
		log.debug("loginSuccessView() invoked.");
		
	} // loginSuccessView
	
//	@GetMapping("/logout")
//	void logoutView() {
//		log.debug("logoutView() invoked.");
//		
//	} // logoutView
	
	
	@GetMapping("/accessDenied")
	void accessDenied() {
		log.debug("accessDenied() invoked.");
		
	} // accessDenied
	
	
} // end class
