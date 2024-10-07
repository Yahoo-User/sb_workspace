package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@ServletComponentScan

//@SpringBootApplication = @SpringBootConfiguration(= @Configuration) + @EnableAutoConfiguration + @ComponentScan
//@SpringBootApplication

//@SpringBootConfiguration
@Configuration
@EnableAutoConfiguration
@ComponentScan(
		excludeFilters = { 
				@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
				@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) 
		}
)	// @ComponentScan
public class Ex04Application
	extends ServletInitializer {

	
	public static void main(String[] args) {
//		SpringApplication.run(Ex04Application.class, args);
		
		// -------------
		SpringApplication app = new SpringApplication(Ex04Application.class);
		
		// 1st. method
//		app.addListeners(new ApplicationListener<ApplicationEvent>() {
//			@Override
//			public void onApplicationEvent(ApplicationEvent event) {
//				log.trace("&&& onApplicationEvent({}) invoked.", event.getClass().getSimpleName());
//			}	// onApplicationEvent
//		});	// .addListeners
		
		// 2nd. method
		app.addListeners(e -> {
//			log.trace("&&& onApplicationEvent({}) invoked.", e.getClass().getSimpleName());
		});	// .addListeners
		
		app.run(args);

		// -------------
		log.trace("main({}) invoked.", Arrays.toString(args));
	}	// main

} // end class
