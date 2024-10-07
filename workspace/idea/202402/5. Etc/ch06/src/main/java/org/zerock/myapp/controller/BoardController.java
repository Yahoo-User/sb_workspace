package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Log4j2
@NoArgsConstructor

@Controller
public class BoardController {


    @GetMapping("/home")
    String home() {
        log.trace("home() invoked.");

        return "home";
    } // home

} // end class
