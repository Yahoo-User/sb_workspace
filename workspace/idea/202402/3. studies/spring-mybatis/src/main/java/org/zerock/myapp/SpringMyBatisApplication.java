package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Arrays;


@Log4j2
@NoArgsConstructor

@ServletComponentScan
@SpringBootApplication
public class SpringMyBatisApplication
		extends ServletInitializer
		implements ApplicationListener<ApplicationEvent> {


	public static void main(String[] args) {
		SpringApplication.run(SpringMyBatisApplication.class, args);

		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		log.trace("onApplicationEvent({}) invoked.", event);
	} // onApplicationEvent

} // end class
