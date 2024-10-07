package org.zerock.myapp.config;

import org.zerock.myapp.util.JDBCConnectionManager;

import lombok.extern.java.Log;


@Log
//@NoArgsConstructor

//@Configuration
public class BoardConfiguration {
	
	
	public BoardConfiguration() {
		log.info("Default constructor invoked.");
	} // Default constructor	
	
	
//	@Bean
	public JDBCConnectionManager getJDBCConnectionManager() {
		log.info("getJDBCConnectionManager() invoked.");
		
		JDBCConnectionManager manager = new JDBCConnectionManager();
		
		manager.setDriverClass("org.h2.Driver");
		manager.setUrl("jdbc:h2:tcp://localhost/~/test");
		manager.setUsername("sa");
		manager.setPassword("");
		
		return manager;
	} // getJDBCConnectionManager

} // end class
