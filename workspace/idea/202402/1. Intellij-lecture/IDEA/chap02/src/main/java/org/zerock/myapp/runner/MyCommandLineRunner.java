package org.zerock.myapp.runner;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;


@Log4j2
@NoArgsConstructor

// `@SessionAttributes(modelKey)` annotation *CANNOT* be applied.
// Because the below class is *NOT* a `@Controller`.
@SessionAttributes("KEY")

@Component
public class MyCommandLineRunner implements CommandLineRunner {


    // `@ModelAttribute(key)` annotation *CANNOT* be applied.
    // Because the below method is *NOT* a `@Controller` class's Method.
    // Only `@Controller` class can declare `@ModelAttribute(key)` method.
//    @ModelAttribute("KEY")                // XX
//    void createModel(Model model) {
//        log.trace("createModel({}) invoked.", model);
//    } // createModel

    @Override
    public void run(String... args) throws Exception {
        log.trace("run({}) invoked.", Arrays.toString(args));

    } // run

} // end class
