package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Arrays;


@Log4j2
@SpringBootApplication
public class Chap03Application
		implements CommandLineRunner, ApplicationRunner, ApplicationListener {


	public static void main(String[] args) {
		SpringApplication.run(Chap03Application.class, args);

//        ---

//        SpringApplication app = new SpringApplication(Chap03Application.class);
//        app.setWebApplicationType(WebApplicationType.NONE);
//        app.setWebApplicationType(WebApplicationType.SERVLET);
//        app.run(args);

//        ---

        log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

	@Override
	public void run(String... args) throws Exception {
		log.trace("run({}) invoked.", Arrays.toString(args));

	} // run

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.trace("run({}) invoked.", args);

	} // run

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		log.trace("onApplicationEvent({}) invoked.", event);

	} // onApplicationEvent

} // end class
