package org.zerock.myapp.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


@Data

//@Entity
//@Table(name="BOARD")
//
//@TableGenerator(
//	name = "BOARD_SEQ_GENERATOR",
//	table = "BOARD_SEQUENCES",
//	pkColumnName = "BOARD_SEQ",
//	initialValue = 0,
//	allocationSize = 1
//)
public class Board3 {
	@Id
	@GeneratedValue(
		strategy = GenerationType.TABLE,
		generator = "BOARD_SEQ_GENERATOR"
	)
	private Long seq;
	
	private String title;
	private String writer;
	private String content;
	
	private Long cnt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date insert_ts;
	
	private Date update_ts;
	
   
	
} // end class
