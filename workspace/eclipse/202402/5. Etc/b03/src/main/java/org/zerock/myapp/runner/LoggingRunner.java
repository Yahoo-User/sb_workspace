package org.zerock.myapp.runner;

import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


// =================================================== //
// org.springframework.boot.ApplicationRunner
// =================================================== //
// Interface used to indicate that a bean should "run" when it is contained within a `SpringApplication`.
// Multiple ApplicationRunner "beans" can be defined within the "same" application context and can be "ordered"
// using the `Ordered interface` or `@Order` annotation.
// =================================================== //

@Log4j2
@NoArgsConstructor

@Component
//@Service
public class LoggingRunner implements ApplicationRunner {
	
	// When using `logback` logging implementation
	private Logger logger=LoggerFactory.getLogger(LoggingRunner.class);


	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.trace("run({}) invoked.", args);

		// 1. When using `logback` logger,
		logger.trace("TRACE");
		logger.debug("DEBUG");
		logger.info("INFO");
		logger.warn("WARN");
		logger.error("ERROR");
		
		log.info("--------------------");
		
		// 2. When using lombok `@Log4j2` logger,
		log.trace("TRACE2");
		log.debug("DEBUG2");
		log.info("INFO2");
		log.warn("WARN2");
		log.error("ERROR2");
		log.fatal("FATAL2");
		
		log.info("--------------------");
		
		log.log(Level.TRACE, "TRACE3");
		log.log(Level.DEBUG, "DEBUG3");
		log.log(Level.INFO, "INFO3");
		log.log(Level.WARN, "WARN3");
		log.log(Level.ERROR, "ERROR3");
		log.log(Level.ALL, "ALL3");
		log.log(Level.OFF, "OFF3");
	} // run

} // end class
