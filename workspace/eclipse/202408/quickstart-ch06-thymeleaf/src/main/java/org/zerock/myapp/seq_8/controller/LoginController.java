package org.zerock.myapp.seq_8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.zerock.myapp.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_4.domain.Member;
import org.zerock.myapp.util.SharedAttributes;

import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@SessionAttributes({SharedAttributes.CREDENTIAL})

@RequestMapping("/security")		// Base URI
@Controller("loginController3")
public class LoginController extends CommonBeanCallbacks {
	
	
	@GetMapping("/logout")
	String logout(
		@SessionAttribute(name = SharedAttributes.CREDENTIAL, required = false) Member member,
		SessionStatus sessionStatus,
		@CookieValue(name = "JSESSIONID", required = false) String sessionId,
		HttpSession session
	) {
		log.trace("logout({}, {}, {}, {}) invoked.", member, sessionStatus, sessionId, session);

		// -----
		sessionStatus.setComplete();
		session.invalidate();
		
		return "redirect:login";
	} // logout

} // end class


