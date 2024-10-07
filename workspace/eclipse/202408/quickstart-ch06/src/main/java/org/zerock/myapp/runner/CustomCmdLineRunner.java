package org.zerock.myapp.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.zerock.myapp.seq_0.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_12.scope.ApplicationScopeBean;
import org.zerock.myapp.seq_12.scope.SingletonScopeBean;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component("customCmdLineRunner")
public class CustomCmdLineRunner
	extends CommonBeanCallbacks implements CommandLineRunner {
	@Autowired(required = false) private SingletonScopeBean singletonScopeBean;					// OK
	@Autowired(required = false) private ApplicationScopeBean applicationScopeBean;				// OK
	
//	@Autowired(required = false) private GlobalSessionScope globalSessionScopeBean;			// XX
//	@Autowired(required = false) private SessionScope sessionScopeBean;								// XX
//	@Autowired(required = false) private RequestScope requestScopeBean;								// XX
	
	@Override
	public void run(String... args) throws Exception {
		log.trace("run({})", Arrays.toString(args));
		
		log.info("  + this.singletonScopeBean: {}", this.singletonScopeBean);
		log.info("  + this.applicationScopeBean: {}", this.applicationScopeBean);
		
//		log.info("  + this.globalSessionScopeBean: {}", this.globalSessionScopeBean);
//		log.info("  + this.sessionScopeBean: {}", this.sessionScopeBean);
//		log.info("  + this.requestScopeBean: {}", this.requestScopeBean);
	} // run

} // end class

