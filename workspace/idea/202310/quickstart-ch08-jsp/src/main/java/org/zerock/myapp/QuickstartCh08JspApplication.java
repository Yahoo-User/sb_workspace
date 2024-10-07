package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;


@Log4j2

@EnableJpaAuditing
@ServletComponentScan
@SpringBootApplication
public class QuickstartCh08JspApplication
		extends ServletInitializer
		implements ApplicationListener<ApplicationEvent> {


	public static void main(String[] args) {
		SpringApplication.run(QuickstartCh08JspApplication.class, args);

//		---
//		SpringApplication app = new SpringApplication(QuickstartCh08JspApplication.class);

		// 1. Banner Mode.
//		app.setBannerMode(Banner.Mode.CONSOLE);
//		app.setBannerMode(Banner.Mode.OFF);

		// 2. Web Application Type.
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
