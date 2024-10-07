package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2

// Class contains `required fields`,
// you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@Controller
public class HomeController {


	@GetMapping("/")
	String root() {
		log.info("root() invoked.");

		return "index";
	} // root
	
	@GetMapping("/home")
	void home() {
		log.trace("home() invoked.");
		
	} // home

	@GetMapping("/hello")
	void hello(Model model) {
		log.trace("hello({}) invoked.", model);

		model.addAttribute("greeting", "Hello, JSP !!!");
	} // hello

} // end class
