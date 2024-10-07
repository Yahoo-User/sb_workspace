package org.zerock.myapp.domain;

import java.time.LocalDateTime;

import lombok.Value;


// `@Value` already marks each field with non-static, package-local fields private.
@Value
public class BoardVOWithLombokValue {
	int seq;
	String title;
	String writer;
	String content;
	LocalDateTime createDate;
	int cnt;

	
} // end class
