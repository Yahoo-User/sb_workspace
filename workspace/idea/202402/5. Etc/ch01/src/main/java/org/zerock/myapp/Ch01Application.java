package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import java.util.Arrays;


@Log4j2

//@ComponentScan(basePackages = { "other" })
@SpringBootApplication
public class Ch01Application {
	private ServerProperties serverProperties;


	// (1) Run As a Web Application
	public static void main(String[] args) {
		SpringApplication.run(Ch01Application.class, args);

		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main


//	============================================= //

/*	// (2) Run As a Java Application
	public static void main(String... args) {
		log.trace("main({}) invoked.", Arrays.toString(args));

		SpringApplication app = new SpringApplication(Ch01Application.class);
		log.info("\t+ app: {}", app);

		app.setWebApplicationType(WebApplicationType.NONE);			// Run as a standalone java application.
//		app.setWebApplicationType(WebApplicationType.SERVLET);		// The same as (1) : As a web application.
//		app.setWebApplicationType(WebApplicationType.REACTIVE);		// XX

		// Turn off Spring Boot Banner
		app.setBannerMode(
			Banner.Mode.CONSOLE
//			Banner.Mode.OFF
		);

		app.run(args);
	} // main*/

} // end class
