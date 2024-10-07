package org.zerock.myapp.security.domain;

import java.io.Serial;
import java.io.Serializable;

import lombok.Value;


@Value
public class UserVO implements Serializable {
	@Serial private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String role;
	
	

} // end class

