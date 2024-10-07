package org.zerock.myapp.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;


@Log4j2

@Component
public class CustomApplicationRunner implements ApplicationRunner {

	
	public CustomApplicationRunner() {
		log.trace("Default Constructor invoked.");
	} // Default constructor

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.trace("run({}) invoked.", args);
	} // run

} // end class

