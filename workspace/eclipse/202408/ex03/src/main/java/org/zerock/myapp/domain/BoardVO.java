package org.zerock.myapp.domain;

import java.time.LocalDateTime;

import lombok.extern.log4j.Log4j2;


@Log4j2

// @NoArgsConstructor, @AllArgsConstructor, @ToString is only supported on a class or an enum.
//@AllArgsConstructor			// XX : Already the record has AllArgsConstructor by default.				<--- ***
//@NoArgsConstructor			// XX
//@ToString 							// XX : Already the record has ToString by default. 							<--- ***
public record BoardVO (
		// A record component content cannot have modifiers.		<--- ***
		// private 		// XX
		int seq, String title, String writer, String content, 	LocalDateTime createDate, int cnt ) {
	
	// A non-canonical constructor must start with an explicit invocation to a constructor.				<--- ***
	public BoardVO() {
		this(1, "title", "writer", "content", LocalDateTime.now(), 0);
		log.trace("non-canonical constructor invoked.");
	} // constructor
	
} // end record


