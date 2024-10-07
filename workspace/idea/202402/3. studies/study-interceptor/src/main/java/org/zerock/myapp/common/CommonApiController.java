package org.zerock.myapp.common;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.stream.Collectors;


@Log4j2
@NoArgsConstructor

@RequestMapping("/api")

@RestController("commonApiController")
public final class CommonApiController {
	@Autowired private RequestMappingHandlerMapping handlerMapping;
	
	
	@GetMapping("/mappings")
	public Map<String, String> getMappings() {
		log.trace("getMappings() invoked.");
		
		return this.handlerMapping.getHandlerMethods()
							.entrySet()
							.stream()
							.collect(Collectors.toMap(
				                        entry -> entry.getKey().toString(),
				                        entry -> entry.getValue().toString() ));
	} // getMappings
	
} // end class


