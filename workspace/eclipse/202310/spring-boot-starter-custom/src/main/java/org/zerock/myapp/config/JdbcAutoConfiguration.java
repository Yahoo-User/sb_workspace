package org.zerock.myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zerock.myapp.util.JdbcConnectionManager;

import lombok.extern.log4j.Log4j2;


@Log4j2

/**
 * --------------------------------------
 * @EnableConfigurationProperties
 * --------------------------------------
 * Enable support for `@ConfigurationProperties` annotatedbeans.
 * 
 * `@ConfigurationProperties` beans can be registered in the standard way (for example using `@Bean` methods) or,
 * for convenience, can be specified directly on this annotation.
 * 
 */
@EnableConfigurationProperties(JdbcConfigurationProperties.class)
@Configuration
public class JdbcAutoConfiguration {
	@Autowired
	private JdbcConfigurationProperties props;
	
	
	public JdbcAutoConfiguration() {
		log.trace("****** Default Constructor Invoked ******");
	} // Default Constructor
	
	@Bean
	@ConditionalOnMissingBean
	public JdbcConnectionManager jdbcConnectionManager() {
		log.trace("jdbcConnectionManager() invoked.");
		
		JdbcConnectionManager jdbcConnMgr = new JdbcConnectionManager();
		jdbcConnMgr.setDriveClass(this.props.getDriverClass());
		jdbcConnMgr.setUrl(this.props.getUrl());
		jdbcConnMgr.setUsername(this.props.getUsername());
		jdbcConnMgr.setPassword(this.props.getPassword());
		
		log.info("\t+ jdbcConnMgr: {}", jdbcConnMgr);
		
		return jdbcConnMgr;
	} // jdbcConnectionManager

} // end class
