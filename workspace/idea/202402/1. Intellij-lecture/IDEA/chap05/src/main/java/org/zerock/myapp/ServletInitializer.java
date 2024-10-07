package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@Log4j2

// Class contains `required fields`,
// you have to `force` NoArgsConstructor. (*****)
@NoArgsConstructor
public class ServletInitializer

	/*
	 * ===================================================
	 * SpringBootServletInitializer
	 * ===================================================
	 * An opinionated `WebApplicationInitializer` to run a `SpringApplication` from a traditional WAR deployment.
	 * Binds `Servlet`, `Filter` and `ServletContextInitializer` beans from the application context to the server.
	 *
	 * To configure the application either override
	 * the `configure(SpringApplicationBuilder)` method (calling `SpringApplicationBuilder.sources(Class...)`) or
	 * make the initializer itself a `@Configuration`.
	 *
	 * If you are using `SpringBootServletInitializer` in combination with other `WebApplicationInitializers`
	 * you might also want to add an `@Ordered` annotation to configure a specific startup order.
	 *
	 * Note that a `WebApplicationInitializer` is only needed if you are building a war file and deploying it.
	 * If you prefer to run an embedded web server then you won't need this at all.
	 */
	extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		log.trace("configure({}) invoked.", application);

		return application.sources(Chap05Application.class);
	} // configure

} // end class
