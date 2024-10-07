package org.zerock.myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zerock.myapp.util.JDBCConnectionManager;

import lombok.extern.java.Log;


@Log
//@NoArgsConstructor

@Configuration
@EnableConfigurationProperties(JDBCConnectionManagerProperties.class)
public class BoardAutoConfiguration
	implements ApplicationRunner {
	
	@Autowired
	private JDBCConnectionManagerProperties properties;
	
	
	public BoardAutoConfiguration() {
		log.info("Default constructor invoked.");
	} // Default constructor
	

	@Bean
	@ConditionalOnMissingBean({JDBCConnectionManager.class})
	public JDBCConnectionManager getJDBCConnectionManager() {
		log.info("getJDBCConnectionManager() invoked.");
		log.info("\t+ properties: " + properties);
		
		JDBCConnectionManager manager = new JDBCConnectionManager();
		
		manager.setDriverClass(properties.getDriverClass());
		manager.setUrl(properties.getUrl());
		manager.setUsername(properties.getUsername());
		manager.setPassword(properties.getPassword());
		
		return manager;
	} // getJDBCConnectionManager

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("run("+args+") invoked.");
	} // run

} // end class
