package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Arrays;


@Log4j2

@ServletComponentScan
@SpringBootApplication
public class QuickstartCh03Application
	extends ServletInitializer
	implements ApplicationListener<ApplicationEvent> {


	public static void main(String[] args) {
		SpringApplication.run(QuickstartCh03Application.class, args);

//		---
//		SpringApplication app = new SpringApplication();

		// 1. Banner Mode Settings.
//		app.setBannerMode(Banner.Mode.OFF);
//		app.setBannerMode(Banner.Mode.CONSOLE);

		// 2. Web Application Type Settings.
//		app.setWebApplicationType(WebApplicationType.NONE);
//		app.setWebApplicationType(WebApplicationType.SERVLET);

//		app.run(args);

//		---
		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		log.trace("onApplicationEvent({}) invoked.", event);

	} // onApplicationEvent
} // end class
