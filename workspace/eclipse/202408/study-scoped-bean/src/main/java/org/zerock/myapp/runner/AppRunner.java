package org.zerock.myapp.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@Component("appRunner")
public class AppRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
			log.trace("run({}) invoked.", args);
	} // run

} // end class


