package org.zerock.myapp.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.myapp.common.CommonBeanCallbacks;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Controller("errorController")
public class ErrorController extends CommonBeanCallbacks {

    @GetMapping("/error")
    void error() {
    	log.trace("error() invoked.");
    }   // View-Controller
	
} // end class
