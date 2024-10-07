package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
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
public class StudyScopedBeanApp implements ApplicationListener<ApplicationEvent> {

	public static void main(String[] args) {
//		SpringApplication.run(StudyScopedBeanApp.class, args);
		
		// ----------------
		SpringApplication app = new SpringApplication(StudyScopedBeanApp.class);
		
		app.setWebApplicationType(WebApplicationType.SERVLET);
		app.setBannerMode(Mode.CONSOLE);
		
//		app.run(args);
		app.run();

		// ----------------
		log.trace("main({}) invoked.", Arrays.toString(args));
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		log.trace("onApplicationEvent({}) invoked.", event.getClass().getSimpleName());
	} // main

	
} // end class

