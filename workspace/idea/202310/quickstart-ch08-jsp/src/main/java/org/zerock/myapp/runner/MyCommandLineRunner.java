package org.zerock.myapp.runner;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Slf4j

// Class contains `required fields`, You have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

// For ordering between console runners implements `CommandLineRunner` or `ApplicationRunner` interfaces.
@Order(1)
@Component
public class MyCommandLineRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        log.trace("run({}) invoked.", Arrays.toString(args));

    } // run

} // end class
