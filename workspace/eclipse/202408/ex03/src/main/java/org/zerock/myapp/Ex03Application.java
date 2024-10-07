package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@ComponentScan(basePackages = { "org.zerock.myapp",  "non_top_level" })
@ServletComponentScan
@SpringBootApplication		// (1) @SpringBootConfiguration (2) @EnableAutoConfiguration (3) @ComponentScan
public class Ex03Application
	extends ServletInitializer
	implements ApplicationRunner, CommandLineRunner {

	
	public static void main(String[] args) {
//		SpringApplication.run(Ex03Application.class, args);
		
		// ------------------
		SpringApplication app = new SpringApplication(Ex03Application.class);
		
		app.addListeners(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				log.trace("&&& onApplicationEvent({}) invoked.", event.getClass().getSimpleName());
			} // onApplicationEvent
		});	// addListeners
		
		app.run(args);

		// ------------------
		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

	@Override
	public void run(String... args) {
		log.trace("CommandLineRunner::run({}) invoked.", Arrays.toString(args));
	} // run

	@Override
	public void run(ApplicationArguments args) {
		log.trace("ApplicationRunner::run({}) invoked.", args);
	} // run

} // end class

