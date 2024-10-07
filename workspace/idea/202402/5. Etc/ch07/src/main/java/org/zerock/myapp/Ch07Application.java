package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;


@Log4j2
@SpringBootApplication
public class Ch07Application {

	
	public static void main(String[] args) {
		SpringApplication.run(Ch07Application.class, args);
		
		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

} // end class
