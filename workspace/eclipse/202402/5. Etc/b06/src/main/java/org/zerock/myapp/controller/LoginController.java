package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.service.MemberService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Controller
public class LoginController {
	
	@Autowired
	private MemberService service;
	
	
	@GetMapping("/login")
	void loginView() {
		log.debug("loginView() invoked.");

	} // loginView
	
	
	@PostMapping("/login")
	String login(Member member, Model model) {
		log.debug("login({}, {}) invoked.", member, model);
		
		Member foundMember = this.service.getMember(member);
		
		if(foundMember!=null && foundMember.getPw().equals(member.getPw())) {
			model.addAttribute("member", foundMember);
			
			return "forward:/getBoardList";
		} else {
			return "redirect:/login";
		} // if-else
	} // login
	
	
	@GetMapping("/logout")
	String logout(SessionStatus sess) {
		log.debug("logout({}) invoked.", sess);
		
		sess.setComplete();
		
		return "redirect:/";
	} // logout

} // end class
