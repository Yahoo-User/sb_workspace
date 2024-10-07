package org.zerock.myapp.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.myapp.annotation.LoginUser;
import org.zerock.myapp.oauth2.SessionUser;

import javax.servlet.http.HttpSession;


@Log4j2
@AllArgsConstructor

@Controller
public class IndexController {
    private HttpSession httpSession;


    @GetMapping("/")
    String index(Model model, @LoginUser SessionUser sessionUser) {
        log.trace("index({}, {}) invoked", model, sessionUser);

        if(sessionUser != null) {
            model.addAttribute("userName", sessionUser.getName());
        } // if

        return "index";
    } // index

} // end class
