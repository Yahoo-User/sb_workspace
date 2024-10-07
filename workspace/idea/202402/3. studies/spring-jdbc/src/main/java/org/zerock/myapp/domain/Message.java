package org.zerock.myapp.domain;

import lombok.Data;

import java.io.Serializable;


@Data
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private String text;


} // end class
