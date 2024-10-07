package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.zerock.myapp.service.BoardService;


@Log4j2
@NoArgsConstructor

@SessionAttributes("__KEY__")
@Controller
public class HomeController {
    @Setter(onMethod_ = @Autowired)
    private BoardService boardService;


    @GetMapping("/")
    String home(Model model) {
        log.trace("home({}) invoked.", model);

        model.addAttribute("_KEY_", "HELLO");
        return "home";
    } // home

    @GetMapping("/testTransaction")
    String testTransaction(Model model) {
        log.trace("testTransaction() invoked.");

        log.info("\t+ this.boardService type: {}", this.boardService.getClass().getName());
        this.boardService.testTransaction();

        model.addAttribute("_KEY_", "testTransaction");
        return "home";
    } // testTransaction

} // end class
