package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

import java.util.Arrays;


@Log4j2

/*
	==============================================
	@ServletComponentScan
	==============================================
	Enables scanning for Servlet components (`filters`, `servlets`, and `listeners`).
	Scanning is only performed when using an `embedded web server`. (***)

	Typically, one of value, `basePackages`, or `basePackageClasses` should be specified
	to control the packages to be scanned for components.
	In their absence, scanning will be performed from the package of the class with the annotation. (***)
 */
@ServletComponentScan

// For ordering between console runners implements `CommandLineRunner` or `ApplicationRunner` interfaces.
@Order(2)

@SpringBootApplication
public class QuickstartCh06JspApplication

	/*
		==============================================
		extends `SpringBootServletInitializer`
		==============================================
		Note that a `WebApplicationInitializer` is only needed if you are building a `war` file and deploying it.
		If you prefer to run an `embedded web server` then you won't need this at all.

	 	// If extends `SpringBootServletInitializer`, Do override below method like this. (***)
		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
			log.trace("configure({}) invoked.", builder);
			return builder.sources(QuickstartCh06JspApplication.class);
		} // configure
	 */

	implements CommandLineRunner, ApplicationListener<ApplicationEvent> {


	public static void main(String[] args) {
		// 1st. method
//		SpringApplication.run(QuickstartCh06JspApplication.class, args);

		// 2nd. method
		SpringApplication app = new SpringApplication(QuickstartCh06JspApplication.class);
		app.setWebApplicationType(WebApplicationType.SERVLET);
		app.run(args);

		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

	@Override
	public void run(String... args) /*throws Exception*/ {
		log.trace("run({}) invoked.", Arrays.toString(args));

	} // run

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		log.trace("onApplicationEvent({}) invoked.", event);

	} // onApplicationEvent

} // end class
