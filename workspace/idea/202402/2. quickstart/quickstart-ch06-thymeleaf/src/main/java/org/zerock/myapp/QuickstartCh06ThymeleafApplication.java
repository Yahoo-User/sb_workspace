package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

import java.util.Arrays;


@Log4j2

// For ordering between console runners implements `CommandLineRunner` or `ApplicationRunner` interfaces.
@Order(2)

@ServletComponentScan
@SpringBootApplication
public class QuickstartCh06ThymeleafApplication
		extends ServletInitializer
		implements CommandLineRunner, ApplicationListener<ApplicationEvent> {


	public static void main(String[] args) {
		SpringApplication.run(QuickstartCh06ThymeleafApplication.class, args);

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
