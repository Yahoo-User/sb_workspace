package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Controller
public class SecurityController {
	
	
	@GetMapping("/")
	String index() {
		log.debug("index() invoked.");
		
		return "index";
//		throw new RuntimeException("TEST");
	} // index
	
	
	@GetMapping("/member")
	void member() {
		log.debug("member() invoked.");
		
	} // member
	
	
	@GetMapping("/manager")
	void manager() {
		log.debug("manager() invoked.");
		
	} // manager
	
	
	@GetMapping("/admin")
	void admin() {
		log.debug("admin() invoked.");
		
	} // admin
	
	
//	=======================
	
	@ExceptionHandler(Exception.class)
	String handleException(Exception e, Model model) {
		log.debug("handleException({}, {}) invoked.");
		
		model.addAttribute("exception", e);
		
		return "error";
	} // handleException

} // end class
