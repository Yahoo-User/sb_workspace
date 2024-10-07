package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@Log4j2

/*
// The below annotation combination equals to the @SpringBootApplication (***)

@SpringBootConfiguration
//@Configuration

@EnableAutoConfiguration
@ComponentScan
*/

@SpringBootApplication
public class Ch02Application {


	public static void main(String[] args) {
		SpringApplication.run(Ch02Application.class, args);

		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

} // end class
