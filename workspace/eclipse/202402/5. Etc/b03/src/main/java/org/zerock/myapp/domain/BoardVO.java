package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Data;


//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode

@Data
public class BoardVO {
	
	private Integer seq;
	
	private String title;
	private String writer;
	private String content;
	private Date createDate = new Date();
	
	private Integer cnt = 0;
	
	
	

} // end class
