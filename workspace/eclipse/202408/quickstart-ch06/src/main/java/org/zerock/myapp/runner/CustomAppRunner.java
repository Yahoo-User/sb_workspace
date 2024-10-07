package org.zerock.myapp.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.zerock.myapp.seq_0.common.CommonBeanCallbacks;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component("customAppRunner")
public class CustomAppRunner extends CommonBeanCallbacks implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.trace("run({}) invoked.", args);
	} //run

} // end class


