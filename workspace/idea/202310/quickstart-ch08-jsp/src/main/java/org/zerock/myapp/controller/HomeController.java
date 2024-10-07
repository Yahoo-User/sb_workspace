package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j

// Class contains `required fields`, you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@Controller
public class HomeController {


//	@GetMapping("/")
//	void index() {;;} // View-Controller

	@GetMapping("/home")
	void home() {;;} // View-Controller

	@GetMapping("/hello")
	void hello(Model model) {
		log.trace("hello({}) invoked.", model);
		model.addAttribute("greeting", "Hello, JSP !!!");
	} // hello

} // end class
