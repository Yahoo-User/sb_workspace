package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@ServletComponentScan
@SpringBootApplication
public class QuickstartCh02AutoConfiguration
	extends ServletInitializer
	implements ApplicationListener<ApplicationEvent> {

	
	public static void main(String[] args) {
		SpringApplication.run(QuickstartCh02AutoConfiguration.class, args);

		// ---------------
//		SpringApplication app = new SpringApplication(QuickstartCh02AutoConfiguration.class);
//		app.addListeners(log::trace);
//		app.run(args);
		
		// ---------------
		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		log.trace("onApplicationEvent({}) invoked.", event);
	} // main

} // end class

