package org.zerock.myapp.runner;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Slf4j
@NoArgsConstructor

@Component
public class LoggingRunner implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.trace("run({}) invoked.", args);

        log.trace("TRACE.");
        log.debug("DEBUG.");
        log.info("INFO.");
        log.warn("WARN.");
        log.error("ERROR.");
    } // run

} // end class
