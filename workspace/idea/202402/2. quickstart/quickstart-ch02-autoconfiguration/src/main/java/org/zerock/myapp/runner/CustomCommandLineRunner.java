package org.zerock.myapp.runner;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.zerock.myapp.jdbc.JDBCProperties;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor																						// @since 4.2
//@NoArgsConstructor

@Component
public class CustomCommandLineRunner implements CommandLineRunner {
	private final JDBCProperties properties;															// @since 4.2
	
	
	@Override
	public void run(String... args) {
		log.trace("run({}) invoked.", Arrays.toString(args));
		log.info("\t+ this.properties: {}", this.properties);
	} // run

} // end class


