package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.extern.log4j.Log4j2;


@Log4j2

@ServletComponentScan
@SpringBootApplication
public class StudyTestApplication extends ServletInitializer {

	
	public static void main(String[] args) {
		SpringApplication.run(StudyTestApplication.class, args);

		// ---------------
//		SpringApplication app = new SpringApplication(StudyTestApplication.class);

/*
		app.addListeners(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				log.trace("onApplicationEvent({}) invoked.", event);
			} // onApplicationEvent
		});	// .addListeners
*/
		
		// ----
		
//		app.addListeners(e -> log.trace(e));
//		app.addListeners(log::trace);
//		app.run(args);
		
		// ---------------
		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

} // end class

