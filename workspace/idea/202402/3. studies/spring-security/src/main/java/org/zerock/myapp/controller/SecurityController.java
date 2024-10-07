package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Log4j2
@NoArgsConstructor

@Controller
public class SecurityController {


    @GetMapping("/")
    String index() {
        log.trace("index() invoked.");

        return "index";
    } // index

    @GetMapping("/member")
    void member() {
        log.trace("member() invoked.");

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        log.info("\t1. authentication: {}", authentication);
        log.info("\t2. principal: {}", authentication.getPrincipal());
        log.info("\t3. credentials: {}", authentication.getCredentials());
        log.info("\t4. details: {}", authentication.getDetails());
        log.info("\t5. name: {}", authentication.getName());
    } // member

    @GetMapping("/manager")
    void manager(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        log.trace("manager({}) invoked.", userDetails);

        if(userDetails != null) {
            log.info("\t+ username: {}", userDetails.getUsername());
            log.info("\t+ password: {}", userDetails.getPassword());
            log.info("\t+ authorities: {}", userDetails.getAuthorities());

            model.addAttribute("Principal", userDetails);
        } // if
    } // manager

    @GetMapping("/admin")
    void admin() {
        log.trace("admin() invoked.");

    } // admin

    @GetMapping("/login")
    void login() {
        log.trace("login() invoked.");

    } // login

    @GetMapping("/loginSuccess")
    void loginSuccess() {
        log.trace("loginSuccess() invoked.");

    } // loginSuccess

    @GetMapping("/accessDenied")
    void accessDenied() {
        log.trace("accessDenied() invoked.");

    } // accessDenied

} // end class
