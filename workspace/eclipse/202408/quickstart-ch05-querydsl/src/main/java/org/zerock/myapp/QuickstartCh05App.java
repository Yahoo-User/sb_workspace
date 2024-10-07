package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@EntityScan
@EnableJpaAuditing
@EnableJpaRepositories

@ServletComponentScan
@ConfigurationPropertiesScan

@SpringBootApplication
public class QuickstartCh05App
	extends ServletInitializer
	implements ApplicationListener<ApplicationEvent> {

	public static void main(String[] args) {
		SpringApplication.run(QuickstartCh05App.class, args);
		log.trace("run({}) invoked.", Arrays.toString(args));
	} // main

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		log.trace("onApplicationEvent(type: {}, source: {}) invoked.", 
//						event.getClass().getSimpleName(), event.getSource().getClass().getSimpleName());
	} // onApplicationEvent

} // end class

