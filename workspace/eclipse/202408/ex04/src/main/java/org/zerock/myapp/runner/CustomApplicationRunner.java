package org.zerock.myapp.runner;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component
public class CustomApplicationRunner
	implements ApplicationRunner, InitializingBean, DisposableBean {
	
	@Override
	public void run(ApplicationArguments args) {
		log.trace("run({}) invoked.", args);
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

