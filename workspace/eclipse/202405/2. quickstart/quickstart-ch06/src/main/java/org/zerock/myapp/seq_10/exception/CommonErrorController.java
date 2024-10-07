package org.zerock.myapp.seq_10.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.myapp.seq_0.common.CommonBeanCallbacks;

import lombok.NoArgsConstructor;


@NoArgsConstructor

@Controller("commonErrorController")
public final class CommonErrorController extends CommonBeanCallbacks {

    @GetMapping("/error")
    void error() {}   // View-Controller
	
} // end class
