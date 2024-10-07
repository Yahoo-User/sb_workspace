package org.zerock.myapp.domain;

import java.time.LocalDateTime;

import lombok.extern.log4j.Log4j2;


@Log4j2

// Note1: About Java Record Class
// 	(1) @AllArgsConstructor - The record already has all arguments constructor by default.
// 	(2) @NoArgsConstructor - This is only supported on a class or an enum.
// 	(3) @ToString 				 	 - The record already has ToString by default.
// Note2:	The record component(=fields) CANNOT have any access modifiers & others like final.
// Note3: A non-canonical constructor MUST start with an explicit invocation to a constructor.
// Note4: User declared non-static(= instance) fields are NOT permitted in a record.
//       	   	 User declared static fields are ONLY permitted in a record.
// Note5: The record has all fluent-style getter method on each record component(= field).


public record BoardVOWithRecordType (
	// Note2:	The record components(=fields) cannot have any access modifiers & others like final.
	int seq, String title, String writer, String content, LocalDateTime createDate, int cnt 
) {
	// Note4: User declared non-static(= instance) fields are NOT permitted in a record.
	//   			 User declared static fields are ONLY permitted in a record.
	// public String name = "Yoseph";				// XX
	// public static String name = "Yoseph";		// OK

	// Note3: A non-canonical constructor MUST start with an explicit invocation to a constructor.
	public BoardVOWithRecordType() {				// OK
		this(1, "title", "writer", "content", LocalDateTime.now(), 0);
		
		log.trace("non-canonical constructor invoked.");
	} // constructor

	// Note5: 	The record has all fluent-style getter method on each record component(= field).
	
} // end record

