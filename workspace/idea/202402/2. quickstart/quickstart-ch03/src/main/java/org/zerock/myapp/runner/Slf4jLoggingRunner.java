package org.zerock.myapp.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@Component
public class Slf4jLoggingRunner implements ApplicationRunner {

	
	@Override
	public void run(ApplicationArguments args) {
		log.trace("run({}) invoked.", args);

		// =================
		// if using @Slf4j
		// =================
		log.trace("1. trace");
		log.debug("2. debug");
		log.info("3. info");
		log.warn("4. warn");
		log.error("5. error");
		log.info("한글");
	} // run

} // end class


