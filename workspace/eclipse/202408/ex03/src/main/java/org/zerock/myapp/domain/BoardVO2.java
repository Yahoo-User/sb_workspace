package org.zerock.myapp.domain;

import java.time.LocalDateTime;

import lombok.Value;


@Value
public class BoardVO2 {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private LocalDateTime createDate;
	
	private int cnt;

	
} // end class
