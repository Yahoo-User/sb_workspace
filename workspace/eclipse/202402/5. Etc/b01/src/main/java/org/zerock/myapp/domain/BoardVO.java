package org.zerock.myapp.domain;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//@Data

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BoardVO {
	
	private Integer seq;
	
	private String title;
	private String writer;
	private String content;
	private Date createDate = new Date();
	
	private Integer cnt = 0;
	
	
	

} // end class
