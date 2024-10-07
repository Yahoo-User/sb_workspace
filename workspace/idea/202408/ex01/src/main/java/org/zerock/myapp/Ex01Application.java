package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Arrays;


@Log4j2

@ServletComponentScan(basePackages="org.zerock.myapp.listener")

// @SpringBootApplication =
// 		1. @SpringBootConfiguration(= @Configuration) +
// 		2. @EnableAutoConfiguration +
// 		3. @ComponentScan

@SpringBootApplication
public class Ex01Application extends ServletInitializer {


	public Ex01Application() {
		log.trace("Default constructor invoked.");
	} // Default Constructor

	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));
		SpringApplication.run(Ex01Application.class, args);
	} // main

} // end class



