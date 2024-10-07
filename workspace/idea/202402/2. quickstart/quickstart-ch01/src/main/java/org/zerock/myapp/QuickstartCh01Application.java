package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;


@Log4j2

@ComponentScan(basePackages = { "org.zerock.myapp", "other" })
@ServletComponentScan
@SpringBootApplication
public class QuickstartCh01Application extends ServletInitializer {


	public static void main(String[] args) {
//		SpringApplication.run(QuickstartCh01Application.class, args);

//		---
		SpringApplication app = new SpringApplication(QuickstartCh01Application.class);

		// 1. Set Banner Mode. { CONSOLE (default) | OFF | application.properties }
//		app.setBannerMode(Banner.Mode.CONSOLE);
//		app.setBannerMode(Banner.Mode.OFF);

		// 2. Set Web Application Type : { NONE | SERVLET (default) | REACTIVE | application.properties }
//		app.setWebApplicationType(WebApplicationType.SERVLET);

		app.run(args);

//		---
		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

} // end class
