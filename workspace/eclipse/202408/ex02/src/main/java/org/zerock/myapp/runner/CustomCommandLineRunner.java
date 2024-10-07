package org.zerock.myapp.runner;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;


@Log4j2

@Component
public class CustomCommandLineRunner implements CommandLineRunner {

	
	public CustomCommandLineRunner() {
		log.trace("Default Constructor invoked.");
	} // Default constructor
	
	@Override
	public void run(String... args) throws Exception {
		log.trace("run({}) invoked.", Arrays.toString(args));
	} // run

} // end class


