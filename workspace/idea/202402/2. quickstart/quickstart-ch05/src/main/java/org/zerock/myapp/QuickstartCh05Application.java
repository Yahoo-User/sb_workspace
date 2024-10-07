package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;


@Log4j2

/*
 * =======================================================
 * 1. @ServletComponentScan
 * =======================================================
 * Enables scanning for Servlet components (filters, servlets, and listeners).
 * Scanning is only performed when using an embedded web server.
 *
 * Typically, one of value, basePackages, or basePackageClasses should be specified to control the packages
 * to be scanned for components.
 *
 * In their absence, scanning will be performed from the package of the class with the annotation.
 *
 * =======================================================
 * 2. CommandLineRunner
 * =======================================================
 *
 * @FunctionalInterface
 * public interface CommandLineRunner
 *
 * Interface used to indicate that a bean should run when it is contained within a SpringApplication.
 *
 * Multiple CommandLineRunner beans can be defined within the same application context
 * and can be ordered using the Ordered interface or `@Order` annotation.
 *
 * If you need access to `ApplicationArguments`
 * instead of the raw String array consider using `ApplicationRunner`.
 *
 * =======================================================
 * 3. ApplicationListener<E extends org.springframework.context.ApplicationEvent>
 * =======================================================
 *
 * @FunctionalInterface
 * public interface ApplicationListener<E extends org.springframework.context.ApplicationEvent>
 *     extends java.util.EventListener
 *
 * Interface to be implemented by application event listeners.
 *
 * Based on the standard `java.util.EventListener` interface for the `Observer design pattern.`
 * As of Spring 3.0, an `ApplicationListener` can generically declare the event type that it is interested in.
 * When registered with a Spring ApplicationContext, events will be filtered accordingly,
 * with the listener getting invoked for matching event objects only.
 *
 * Type parameters:
 * <E> – the specific `ApplicationEvent` subclass to listen to
 */

/*
 * --------------------------------
 * @EnableJpaRepositories
 * --------------------------------
 * Annotation to enable JPA repositories.
 * Will scan the package of the annotated configuration class for Spring Data repositories by default.
 */
@EnableJpaRepositories

@ServletComponentScan
@SpringBootApplication
public class QuickstartCh05Application
		extends ServletInitializer
		implements ApplicationListener<ApplicationEvent> {


	public static void main(String[] args) {
		SpringApplication.run(QuickstartCh05Application.class, args);

//		---

/*
		SpringApplication app = new SpringApplication(QuickstartCh05Application.class);

		// * NOTE * : `spring.main.web-application-type` attribute
		// defined in the `application.properties` file is preemptive.

		app.setWebApplicationType(WebApplicationType.NONE);
		app.setWebApplicationType(WebApplicationType.SERVLET);

		app.run(args);
*/

//		---

		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		log.trace("onApplicationEvent({}) invoked.", event);

	} // onApplicationEvent

} // end class
