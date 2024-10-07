package org.zerock.myapp.seq_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.myapp.common.CommonBeanCallbacks;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Controller("helloController")
public class HelloController extends CommonBeanCallbacks {
	
	@GetMapping("/hello")
	void hello(Model model) {
		log.trace("hello({}) invoked.", model);
		model.addAttribute("greeting", "Hello, Thymeleaf.");
	} // hello
	
} // end class

