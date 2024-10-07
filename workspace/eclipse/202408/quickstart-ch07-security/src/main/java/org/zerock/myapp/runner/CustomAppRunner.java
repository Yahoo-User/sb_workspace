package org.zerock.myapp.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.zerock.myapp.common.CommonBeanCallbacks;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component("customAppRunner")
public class CustomAppRunner
	extends CommonBeanCallbacks implements ApplicationRunner {
//	@Autowired private RequestMappingHandlerMapping handlerMapping;
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.trace("run({}) invoked.", args);
		
		/*
		Map<String, String> requestMappings = 
				this.handlerMapping.getHandlerMethods().entrySet().stream().collect(
						Collectors.toMap(
								entry -> entry.getKey().toString(), 
								entry -> entry.getValue().toString()
						));
		
		log.info("==================================");
		log.info("Request Mapping Table");
		log.info("==================================");
		requestMappings.forEach(log::info);
		log.info("==================================");
		*/
	} // run

} // end class

