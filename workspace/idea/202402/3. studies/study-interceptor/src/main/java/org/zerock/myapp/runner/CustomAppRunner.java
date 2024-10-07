package org.zerock.myapp.runner;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Log4j2
@NoArgsConstructor

@Component("customAppRunner")
public class CustomAppRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        log.trace("run({}) invoked.", args);
    } // run

} // end class

