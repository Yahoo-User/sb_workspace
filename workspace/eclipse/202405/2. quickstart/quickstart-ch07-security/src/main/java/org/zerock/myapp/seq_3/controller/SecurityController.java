package org.zerock.myapp.seq_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/security")

@Controller("securityController")
public class SecurityController {
	
	@GetMapping("/root")
	void root() {
		log.trace("root() invoked.");
	} // root
	
	@GetMapping("/member")
	void member() {
		log.trace("member() invoked.");
	} // member
	
	@GetMapping("/user")
	void user() {
		log.trace("user() invoked.");
	} // user
	
	@GetMapping("/admin")
	void admin() {
		log.trace("admin() invoked.");
	} // admin
	
	@GetMapping("/accessDenied")
	void forbidden() {
		log.trace("forbidden() invoked.");
	} // forbidden
	
	@GetMapping("/authenticationFailed")
	void authenticationFailed() {
		log.trace("authenticationFailed() invoked.");
	} // authenticationFailed

	
	// Local Controller Exception Handler
	@ExceptionHandler(Exception.class)
	String handleLocalException(Exception e, Model model) {
		log.trace("handleLocalException({}, {}) invoked.", e.toString(), model);
		
		model.addAttribute(SharedAttributes.EXCEPTION, e);
		model.addAttribute(SharedAttributes.STACKTRACE, e.getStackTrace());
		
		return "/errors/localError.html";
	} // handleLocalError

} // end class

