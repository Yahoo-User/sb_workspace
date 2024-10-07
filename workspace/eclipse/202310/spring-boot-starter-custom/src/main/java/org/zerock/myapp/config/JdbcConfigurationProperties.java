package org.zerock.myapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Data

@ConfigurationProperties(prefix = "custom")
public class JdbcConfigurationProperties {
	private String driverClass;
	private String url;
	private String username;
	private String password;
	
	
	public JdbcConfigurationProperties() {
		log.trace("****** Default Constructor Invoked ******");
	} // Default Constructor

} // end class
