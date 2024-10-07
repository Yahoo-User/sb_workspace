package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Arrays;


@Log4j2

@ServletComponentScan
@SpringBootApplication
public class SpringSecurityApplication implements ApplicationRunner, CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);

        log.trace("main({}) invoked.", Arrays.toString(args));
	} // main


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.trace("run({}) invoked.", args);

    } // run

    @Override
    public void run(String... args) throws Exception {
        log.trace("run({}) invoked.", Arrays.toString(args));

    } // run

} // end class
