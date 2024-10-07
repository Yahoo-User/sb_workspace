package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2

// Class contains `required fields`, You have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@Controller
public class SecurityController {
	
	
	@GetMapping("/")
	String index() {
		log.trace("index() invoked.");
		
		return "index";
	} // index
	
	
	@GetMapping("/member")
	void member() {
		log.trace("member() invoked.");
		
	} // member
	
	
	@GetMapping("/manager")
	void manager() {
		log.trace("manager() invoked.");
		
	} // manager
	
	
	@GetMapping("/admin")
	void admin() {
		log.trace("admin() invoked.");
		
	} // admin

} // end class
