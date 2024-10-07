package org.zerock.myapp.controller;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@Controller("homeController")
public class HomeController {
	
	
	@GetMapping("/home")
	String home(Model model, @AuthenticationPrincipal OAuth2User oauth2User) {
		log.trace("home(mode, {}) invoked.", oauth2User);
		
		Map<String, Object> userAttributes = oauth2User.getAttributes();
		userAttributes.forEach((k, v) -> log.info("\t+ {}({})", k, v));
		
        model.addAttribute("name", oauth2User.getName());
		return "home";
	} // home
	
} // end class




