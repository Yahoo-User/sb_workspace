package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.service.MemberService;


@Log4j2
@NoArgsConstructor

@SessionAttributes("member")
@Controller
public class LoginController {
    @Autowired private MemberService memberService;


    @GetMapping("/login")
    void loginView() {      // View-Controller
        log.trace("loginView() invoked.");

    } // loginView

    @PostMapping("/login")
    String login(Member member, Model model) {
        log.trace("login(Command Object: {}, {}) invoked.", member, model);

        Member foundMember = this.memberService.getMember(member);

        // Do Authenticate.
        if( foundMember != null &&
            foundMember.getId().equals(member.getId()) &&
            foundMember.getPassword().equals(member.getPassword())) {   // If login succeeded,
            log.info("\t+ Login Success.");

            model.addAttribute("member", foundMember);

//            return "forward:getBoardList";        // XX : 405 Not Supported Method.
            return "redirect:getBoardList";         // OK
        } else {    // If login failed,
            log.info("\t+ Login Failure.");

//            return "forward:login";               // XX : 405 Not Supported Method.
            return "redirect:login";                // OK
        } // if-else
    } // login

    @GetMapping("/logout")
    String logout(SessionStatus sessionStatus) {
        log.trace("logout({}) invoked.", sessionStatus);

        sessionStatus.setComplete();
        return "redirect:/";
    } // logout


} // end class
