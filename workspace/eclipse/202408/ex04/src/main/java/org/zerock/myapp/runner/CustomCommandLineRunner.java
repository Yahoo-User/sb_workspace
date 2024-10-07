package org.zerock.myapp.runner;

import java.util.Arrays;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component
public class CustomCommandLineRunner
	implements CommandLineRunner, InitializingBean, DisposableBean {
	
	@Override
	public void run(String... args) throws Exception {
		log.trace("run({}) invoked.", Arrays.toString(args));
	} // run

	@Override
	public void destroy() {
		log.trace("destroy() invoked.");
	} // destroy

	@Override
	public void afterPropertiesSet() {
		log.trace("afterPropertiesSet() invoked.");
	} // afterPropertiesSet

} // end class
