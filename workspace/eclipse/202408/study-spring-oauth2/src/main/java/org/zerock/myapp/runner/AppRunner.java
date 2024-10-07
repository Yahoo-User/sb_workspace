package org.zerock.myapp.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@Component("appRunner")
public class AppRunner implements ApplicationRunner {
	
	@PostConstruct
	void postConstructor() {
		log.trace("postConstruct() invoked.");
	} // postConstructor
	
	@PreDestroy
	void preDestroy() {
		log.trace("preDestroy() invoked.");
	} // preDestroy

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.trace("run({}) invoked.", args);
	} // run

} // end class

