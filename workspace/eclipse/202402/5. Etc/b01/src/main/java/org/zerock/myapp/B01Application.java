package org.zerock.myapp;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages= { "org.zerock.myapp", "org.zerock.other" })

@SpringBootApplication
public class B01Application {

	
	public static void main(String[] args) {
		// 1. Run as a Web Application
//		SpringApplication.run(B01Application.class, args);
		
		// 2. Run as a Java Application
		SpringApplication springApp = new SpringApplication(B01Application.class);
		
		springApp.setWebApplicationType(WebApplicationType.NONE);
//		springApp.setWebApplicationType(WebApplicationType.SERVLET);
		
		springApp.setBannerMode(Banner.Mode.OFF);
		
		springApp.run(args);
	} // main

} // end class
