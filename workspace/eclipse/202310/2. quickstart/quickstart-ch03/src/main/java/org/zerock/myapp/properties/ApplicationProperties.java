package org.zerock.myapp.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import lombok.Data;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Data

@ConfigurationProperties(prefix = "user-defined")
public class ApplicationProperties {
	private String name;
	private String age;
	
	
	@ConstructorBinding
	public ApplicationProperties(String name, String age) {
		log.trace("constructor({}, {}) invoked.", name, age);
		
		this.name = name;
		this.age = age;
	} // Constructor
	
} // end class

