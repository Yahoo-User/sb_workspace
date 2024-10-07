package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Log4j2
@NoArgsConstructor

@Controller
public class HomeController {


    @GetMapping("/")
    String home(HttpSession session, Model model) {
        log.trace("home({}, {}) invoked.", session, model);

        session.setAttribute("_KEY_", "YOSEPH");
        model.addAttribute("YOSEPH", "HELLO");

        return "home";
    } // home

} // end class
