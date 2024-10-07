package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Log4j2
@NoArgsConstructor

@SessionAttributes("__KEY__")
@Controller
public class HomeController {


    @GetMapping("/")
    String home(Model model) {
        log.trace("home({}) invoked.", model);

        model.addAttribute("_KEY_", "HELLO");
        return "home";
    } // home

} // end class
