package org.zerock.myapp.runner;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


//@Log4j2
@Slf4j
@NoArgsConstructor

//@Service
@Component
public class LoggingRunner implements ApplicationRunner {
    private final Logger logger = LoggerFactory.getLogger(LoggingRunner.class);


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.trace("run({}) invoked.", args);

        logger.trace("TRACE");
        logger.debug("DEBUG");
        logger.info("INFO");
        logger.warn("WARN");
        logger.error("ERROR");

//        log.fatal("1. FATAL");            // XX : If `@Slf4j` used, NO method.
        log.trace("2. TRACE");
        log.debug("3. DEBUG");
        log.info("4. INFO");
        log.warn("5. WARN");
        log.error("6. ERROR");
    } // run

} // end class
