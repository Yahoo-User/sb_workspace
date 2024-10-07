package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.extern.log4j.Log4j2;


@Log4j2

// Default base packages : current package
@ServletComponentScan
//@ServletComponentScan(basePackages = {"org.zerock.myapp"})

/**
 * @SpringBootApplication = 
 * 		1. @SpringBootConfiguration (= @Configuration)
 * 		2. @EnableAutoConfiguration (= @AutoConfigurationPackage)
 * 		3. @ComponentScan
 */
@SpringBootApplication
public class Ex02Application extends ServletInitializer {
	
	// This run class also is registered as a bean into spring beans container 
	// by @SpringBootConfiguration annotation.
	public Ex02Application() {
		log.trace("Default Constructor invoked.");
	} // Default constructor

	public static void main(String[] args) {
		// 1st. method: run as a web application with embedded WAS.
		SpringApplication.run(Ex02Application.class, args);
		
		// 2nd. method: run as a java application.
		// The related properties in application.properties
		// takes precedence over the related codes.	(***)
		
//		SpringApplication app = new SpringApplication(Ex02Application.class);
		
//		app.setWebApplicationType(WebApplicationType.SERVLET);		// Run as a Web Application with embedded WAS.
//		app.setWebApplicationType(WebApplicationType.NONE);			// Run as a Java Application.
		
//		app.setBannerMode(Mode.OFF);								// Banner OFF
//		app.setBannerMode(Mode.CONSOLE);							// Banner to Console

//		app.run(args);
		
		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

} // end class

