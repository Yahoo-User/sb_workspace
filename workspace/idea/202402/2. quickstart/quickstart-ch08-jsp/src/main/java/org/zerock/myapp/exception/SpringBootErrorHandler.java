package org.zerock.myapp.exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// Class contains `required fields`, you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@Controller
public class SpringBootErrorHandler {


    @GetMapping("/error")
    void error() {;;}   // View-Controller

} // end class
