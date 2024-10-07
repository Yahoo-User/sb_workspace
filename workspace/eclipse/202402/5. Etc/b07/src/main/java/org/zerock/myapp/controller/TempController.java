package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Controller
public class TempController {
	
	
	@GetMapping("/test")
	void test() {
		log.debug("test() invoked.");
		
		throw new RuntimeException("TempController.test");
	} // test

} // end class
