package org.zerock.myapp.runner;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component
public class UserCommandLineRunner implements CommandLineRunner {

	
	@Override
	public void run(String... args) throws Exception {
		log.trace("run({}) invoked.", Arrays.toString(args));
	} // run

} // end class


