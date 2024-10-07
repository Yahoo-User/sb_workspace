package org.zerock.myapp.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.myapp.common.CommonBeanCallbacks;

import lombok.NoArgsConstructor;


@NoArgsConstructor

/**
 * *Important :
 * 		The global exception handler with @ControllerAdvice (or @RestControllerAdvice) + @ExceptionHandler precedes
 * 		the exception handler with mapped to the"/error" with (@Controller + `ErrorController` Interface).
 */

@Controller("commonErrorController")
public class CommonErrorController extends CommonBeanCallbacks {

    @GetMapping("/error")
    void error() {}   // View-Controller
	
} // end class
