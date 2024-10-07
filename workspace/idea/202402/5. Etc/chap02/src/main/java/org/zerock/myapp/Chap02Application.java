package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Arrays;


@Log4j2

@ServletComponentScan
@SpringBootApplication
public class Chap02Application
	extends ServletInitializer
	implements CommandLineRunner, ApplicationListener<ApplicationEvent> {


	public static void main(String[] args) {
//		SpringApplication.run(Chap02Application.class, args);		// 1st. method with embedded tomcat.

//		----
		SpringApplication app = new SpringApplication(Chap02Application.class);	// 2nd. method without embedded tomcat.

//		app.setWebApplicationType(WebApplicationType.NONE);			// without embedded tomcat.
		app.setWebApplicationType(WebApplicationType.SERVLET);		// with embedded tomcat.

		app.run(args);

//		----
		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

	@Override
	public void run(String... args) throws Exception {
		log.trace("run({}) invoked.", Arrays.toString(args));

	} // run

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		log.trace("onApplicationEvent({}) invoked.", event);

	} // onApplicationEvent

} // end class
