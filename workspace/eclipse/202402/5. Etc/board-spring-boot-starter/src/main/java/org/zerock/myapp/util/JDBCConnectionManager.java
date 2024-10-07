package org.zerock.myapp.util;

import lombok.Data;
import lombok.extern.java.Log;


@Log
@Data
public class JDBCConnectionManager {
	private String driverClass;
	private String url;
	private String username;
	private String password;
	
	
	public JDBCConnectionManager () {
		log.info("Default Constructor invoked.");
		
	} // Default Constructor
	

} // end class
