package org.zerock.myapp.common;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@RequestMapping("/api")

@RestController("commonApiController")
public class CommonApiController {
	@Autowired(required = true) private RequestMappingHandlerMapping handlerMapping;
	
	
	@GetMapping("/mappings")
	public Map<String, String> getMappings() {
		log.trace("getMappings() invoked.");
		
		return this.handlerMapping
							.getHandlerMethods()
							.entrySet()
							.stream()
							.collect(Collectors.toMap(
											e -> e.getKey().toString(),
											e -> e.getValue().toString()));
	} // getMappings

} // end class
