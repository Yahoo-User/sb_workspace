package org.zerock.myapp.runner;

import org.apache.logging.log4j.Level;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component
public class Log4j2LoggingRunner implements ApplicationRunner {

	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.trace("run({}) invoked.", args);
		
		// ==========================
		log.fatal("1. fatal");								// Equals to "error" level.
		log.trace("2. trace");
		log.debug("3. debug");
		log.info("4. info");
		log.warn("5. warn");
		log.error("6. error");
		log.printf(Level.OFF, "7. OFF");			// Equals to "error" level.
	} // run

} // end class

