package org.zerock.myapp.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.zerock.myapp.jdbc.JDBCConnectionDetails;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor																					// @since 4.2
//@NoArgsConstructor

@Component
public class CustomApplicationRunner implements ApplicationRunner {
	private final JDBCConnectionDetails details;												// @since 4.2
	
	
	@Override
	public void run(ApplicationArguments args) {
		log.trace("run({}) invoked.", args);
		log.info("\t+ this.details: {}", this.details);
	} // run

} // end class

