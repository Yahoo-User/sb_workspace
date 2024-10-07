package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;


@Log4j2
@NoArgsConstructor

@ServletComponentScan

@EntityScan
@EnableJpaAuditing
@EnableJpaRepositories

//@EnableConfigurationProperties
@ConfigurationPropertiesScan

@SpringBootApplication
public class StudyJwtApp implements ApplicationListener<ApplicationEvent> {


	public static void main(String[] args) {
		SpringApplication.run(StudyJwtApp.class, args);
		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		log.trace("onApplicationEvent({}) invoked.", event.getClass().getSimpleName());
	} // onApplicationEvent

} // end class

