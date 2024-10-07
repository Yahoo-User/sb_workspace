package org.zerock.myapp.seq_3.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.zerock.myapp.seq_5.properties.UserDefinedProperties;
import org.zerock.myapp.seq_5.properties.UserDefinedProperties2;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component("customCommandLineRunner")
public class CustomCommandLineRunner implements CommandLineRunner {
	@Autowired(required = false)
	private UserDefinedProperties userProperties;
	
	@Autowired(required = false)
	private UserDefinedProperties2 userProperties2;

	
	@Override
	public void run(String... args) throws Exception {
		log.trace("run({}) invoked.", Arrays.toString(args));
		
		log.info("\t+ userProperties: {}", this.userProperties);
		log.info("\t+ userProperties2: {}", this.userProperties2);
	} // run

} // end class


