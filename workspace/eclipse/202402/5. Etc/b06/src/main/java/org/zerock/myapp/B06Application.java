package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;


@Log4j2
@SpringBootApplication
public class B06Application {

	
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		SpringApplication.run(B06Application.class, args);
		
//		SpringApplication app = new SpringApplication(B06Application.class);
//		app.setWebApplicationType(WebApplicationType.SERVLET);
//		app.run(args);
	} // main

} // end class
