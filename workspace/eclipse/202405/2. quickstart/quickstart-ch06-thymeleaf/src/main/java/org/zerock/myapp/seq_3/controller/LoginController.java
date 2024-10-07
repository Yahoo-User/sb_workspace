package org.zerock.myapp.seq_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.common.CommonBeanCallbacks;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/security")		// Base URI

@Controller("loginController1")
public class LoginController extends CommonBeanCallbacks {
	
	// Replace the below view controller with View-Controller configuration by `WebMvcConfigurer`.
	// Please refer to the 'CommonViewControllerConfig.java'.
	
	@GetMapping("/login")
	void login() {										// View-Controller
		log.trace("login() invoked.");
	} // login

} // end class


