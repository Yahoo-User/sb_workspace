package org.zerock.myapp.security.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.zerock.myapp.security.domain.UserDTO;
import org.zerock.myapp.security.domain.UserVO;

import lombok.Cleanup;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Log4j2

@ToString
public class UserDAO {
//	@Resource(name = "java:comp/env/jdbc/h2/test")									// XX, Injection
	private DataSource dataSource;
	
	
	// Important: 
	//		The `@PostConstruct` & `@PreDestroy` annotations do work *Only in the java *Servlet class.	
	public UserDAO() throws Exception {
		 log.trace("<init> () invoked.");
		 
		 Context ctx = new InitialContext();
		 Objects.requireNonNull(ctx);
		 
		 Object obj = ctx.lookup("java:comp/env/jdbc/h2/test");						// OK, JNDI lookup
		 if(obj instanceof DataSource dataSource) {
			 this.dataSource = dataSource;
		 } // if
		 
		 log.info("\t+ this.dataSource: {}", this.dataSource);
	} // Default Constructor


	public UserVO selectByUsername(UserDTO dto) throws Exception {
		log.trace("selectByUsername({}) invoked.", dto);
		
		Objects.requireNonNull(dto);
		
		if(dto.getUsername() == null || "".equals(dto.getUsername())) {
			throw new IllegalArgumentException("username is null ot empty.");
		} // if
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		log.info("\t+ conn: {}", conn);

		String sql = "SELECT * FROM t_user WHERE username = ? and password = ?";
		
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto.getUsername());
		pstmt.setString(2, dto.getPassword());
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {	// if found.
			String username = rs.getString("username");
			String password = rs.getString("password");
			String role = rs.getString("role");
			
			return new UserVO(username, password, role);
		} else {				// if Not found.
			return null;
		} // if-else
	} // selectByUsername

} // end class
