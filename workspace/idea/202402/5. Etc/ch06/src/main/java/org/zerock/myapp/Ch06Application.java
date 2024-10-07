package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@Log4j2
@SpringBootApplication
public class Ch06Application {


	public static void main(String[] args) {
		SpringApplication.run(Ch06Application.class, args);

		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

} // end class
