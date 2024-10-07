package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@ServletComponentScan

// Note: This annotation have a relationship with `@CreatedDate`, `@LastModifiedDate` annotations.
@EnableJpaAuditing

// Note: This annotation will scan the package of the annotated configuration class for Spring Data repositories by default.
//@EnableJpaRepositories(basePackages = "org.zerock.myapp")

// Note: This annotation configures the base packages used by auto-configuration when scanning for entity classes. 
@EntityScan

//@EnableConfigurationProperties																	// OK, But No Configuration Properties Enabled.
//@EnableConfigurationProperties(UserDefinedProperties.class)					// OK

@ConfigurationPropertiesScan																		// OK
//@ConfigurationPropertiesScan(basePackages = { "org.zerock.myapp" })	// OK

@SpringBootApplication
public class QuickstartCh05Application extends ServletInitializer {

	
	public static void main(String[] args) {
		// ---------------
		//	1st. method
		// ---------------
		SpringApplication.run(QuickstartCh05Application.class, args);
		
		// ---------------
		//	2nd. method
		// ---------------
//		SpringApplication app = new SpringApplication(QuickstartCh05Application.class);
//		app.setWebApplicationType(WebApplicationType.SERVLET);
//		app.setBannerMode(Mode.CONSOLE);
		
		// 1st. method
//		app.addListeners(new ApplicationListener<ApplicationEvent>() {
//			@Override
//			public void onApplicationEvent(ApplicationEvent event) {
//				log.trace("onApplicationEvent({}) invoked.", event);
//			} // onApplicationEvent
//		});	// .addListeners
		
		// 2nd. method
//		app.addListeners(e -> {
//			log.trace("addListeners({}) invoked.", e);
//		});	// .addListeners
		
//		app.run();
//		app.run(args);

		// ---------------
		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

} // end class

