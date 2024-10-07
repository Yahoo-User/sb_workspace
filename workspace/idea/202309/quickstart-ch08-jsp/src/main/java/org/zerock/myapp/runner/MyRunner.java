package org.zerock.myapp.runner;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Log4j2

// Class contains `required fields`,
// you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@Component
public class MyRunner
    implements CommandLineRunner, ApplicationRunner {


    @Override
    public void run(String... args) throws Exception {
        log.trace("run({}) invoked.", Arrays.toString(args));

    } // run

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.trace("run({}) invoked.", args);

    } // run

} // end class
