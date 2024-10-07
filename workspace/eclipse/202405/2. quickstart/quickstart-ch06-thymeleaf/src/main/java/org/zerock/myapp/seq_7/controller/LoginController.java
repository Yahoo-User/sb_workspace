package org.zerock.myapp.seq_7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_4.domain.Member;
import org.zerock.myapp.seq_6.service.MemberService;
import org.zerock.myapp.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@SessionAttributes({SharedAttributes.CREDENTIAL})

@RequestMapping("/security")		// Base URI
@Controller("loginController2")
public class LoginController extends CommonBeanCallbacks {
	@Autowired(required = true) private MemberService memberService;
	
	
	@PostMapping("/login")
	String loginProcess(Member member, @RequestParam(name = "username", required = false) String username, Model model, RedirectAttributes rttrs) {
		log.trace("loginProcess({}, {}, model) invoked.", member, username);
		
		// ----
		if(username != null && !"".equals(username)) member.setName(username);
		
		Member foundMember = this.memberService.findByName(member.getName());
		
		if(foundMember != null && foundMember.getPassword().equals(member.getPassword())) {
			model.addAttribute(SharedAttributes.CREDENTIAL, foundMember);		// Add a Found Member To Session
			return "redirect:/board/getBoardList";
		} else {
//			rttrs.addFlashAttribute(SharedAttributes.LOGIN_ID, member.getName());
			rttrs.addAttribute(SharedAttributes.LOGIN_ID, member.getName());
			return "redirect:login";
		} // if-else
	} // loginProcess
	

} // end class


