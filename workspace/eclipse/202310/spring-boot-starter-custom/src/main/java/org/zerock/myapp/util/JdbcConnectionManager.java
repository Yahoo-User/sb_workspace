package org.zerock.myapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.Data;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Data

public class JdbcConnectionManager {
	private String driveClass;
	private String url;
	private String username;
	private String password;
	
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		log.trace("getConnection() invoked.");
		
		Class.forName(driveClass);
		Connection conn = DriverManager.getConnection(url, username, password);
		
		return conn;
	} // getConnection
	
} // end class
