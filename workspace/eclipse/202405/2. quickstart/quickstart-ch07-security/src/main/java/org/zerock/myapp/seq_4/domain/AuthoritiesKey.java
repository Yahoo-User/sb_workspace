package org.zerock.myapp.seq_4.domain;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;


@Data
public class AuthoritiesKey implements Serializable {
	@Serial private static final long serialVersionUID = 1L;
	
	private String username;
	private String authority;
	

} // end class
