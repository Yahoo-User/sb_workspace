package org.zerock.myapp.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import lombok.Data;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Data

@ConfigurationProperties(prefix = "server")
public class SpringBootProperties {
	private String address;
	private Integer port;

	
	@ConstructorBinding
	public SpringBootProperties(String address, Integer port) {
		log.trace("Constructor({}, {}) invoked.", address, port);
		
		this.address = address;
		this.port = port;
	} // constructor
	
} // end class

