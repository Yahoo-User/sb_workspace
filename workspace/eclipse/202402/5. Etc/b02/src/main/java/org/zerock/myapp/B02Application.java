package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

// (1) @SpringBootApplication == 
//               @SpringBootConfiguration + 
//               @EnableAutoConfiguration + 
//               @ComponentScan  (***)
//@SpringBootApplication


// (2) @SpringBootConfiguration == @Configuration  (***)
//@SpringBootConfiguration
@Configuration


// (4) @EnableAutoConfiguration relate to the Spring Boot AutoConfigure.
//     This annotation loads all Spring Boot internal beans objects into the spring beans container. (***)
@EnableAutoConfiguration


// (3) @ComponentScan loads all bean objects including the below annotations into the spring context:
//     That is, loading all `user-defined beans object` into the spring beans container. (***)
//     a. @Configuration
//     b. @Repository
//     c. @Service
//     d. @Controller
//     e. @RestController
//     f. @Component
//     g. ...
@ComponentScan
public class B02Application {
	

	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		SpringApplication.run(B02Application.class, args);
	} // main

} // end class
