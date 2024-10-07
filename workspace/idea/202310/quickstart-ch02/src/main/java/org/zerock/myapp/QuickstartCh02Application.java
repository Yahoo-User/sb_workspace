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
public class QuickstartCh02Application
		extends ServletInitializer
		implements ApplicationListener<ApplicationEvent> {


	public static void main(String[] args) {
		SpringApplication.run(QuickstartCh02Application.class, args);

//		---
//		SpringApplication app = new SpringApplication(QuickstartCh02Application.class);

		// 1. Configure Banner Mode.
//		app.setBannerMode(Banner.Mode.CONSOLE);
//		app.setBannerMode(Banner.Mode.OFF);

		// 2. Configure Web Application Type.
//		app.setWebApplicationType(WebApplicationType.SERVLET);
//		app.setWebApplicationType(WebApplicationType.NONE);

//		app.run(args);

//		---
		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		log.trace("onApplicationEvent({}) invoked.", event);

	} // onApplicationEvent

} // end class
