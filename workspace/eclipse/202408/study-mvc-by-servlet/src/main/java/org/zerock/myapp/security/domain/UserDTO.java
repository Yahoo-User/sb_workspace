package org.zerock.myapp.security.domain;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;


@Data
public class UserDTO implements Serializable {
	@Serial private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	

} // end class

