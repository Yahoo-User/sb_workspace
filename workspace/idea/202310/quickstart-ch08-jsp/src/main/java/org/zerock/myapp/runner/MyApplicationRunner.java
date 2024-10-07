package org.zerock.myapp.runner;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Slf4j

// Class contains `required fields`, You have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

// For ordering between console runners
//     implements `CommandLineRunner` or `ApplicationRunner` interfaces.
@Order(2)
@Component
public class MyApplicationRunner implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.trace("run({}) invoked.", args);

    } // run

} // end class
