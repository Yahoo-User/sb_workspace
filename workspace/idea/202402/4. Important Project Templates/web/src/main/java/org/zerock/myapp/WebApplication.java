package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@Log4j2
@SpringBootApplication
public class WebApplication {


	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);

        log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

} // end class
