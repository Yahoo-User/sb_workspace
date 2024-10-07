package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Data

@Entity
public class Board implements Serializable {
	@Serial private static final long serialVersionUID = 1L;

	// Important:
	// 	If `@Id` annotation might NOT be used with `@GeneratedValue`,
	//    and then, when persisting new entity object, the entity must have new id * manually *.
	@Id
	
	// H2 database uses a SEQUENCE which generate a value of the primary key.
//	@GeneratedValue
	private Long seq;
	
	private String title;
	private String writer;
	private String content;
	private LocalDateTime createDate;
	private Date updateDate;
	private Long cnt;
	
	
   
} // end class


