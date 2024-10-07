package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

//@EnableConfigurationProperties
@ConfigurationPropertiesScan

@ServletComponentScan

@SpringBootApplication
public class StudySpringOauth2App implements ApplicationListener<ApplicationEvent> {

	public static void main(String[] args) {
		SpringApplication.run(StudySpringOauth2App.class, args);
		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		log.trace("onApplicationEvent({}) invoked.", event.getClass().getSimpleName());
	} // main

} // end class

