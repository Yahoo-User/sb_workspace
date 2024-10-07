package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@Log4j2
@SpringBootApplication
public class Jpa2Application {

	public static void main(String[] args) {
		// 1. Started with web environments.
//		SpringApplication.run(Jpa2Application.class, args);

		// 2. Started as a standalone application.
		SpringApplication app = new SpringApplication(Jpa2Application.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);

		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

} // end class
