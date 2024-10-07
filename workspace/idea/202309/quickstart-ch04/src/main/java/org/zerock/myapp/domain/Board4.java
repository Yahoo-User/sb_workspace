package org.zerock.myapp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;


@Data

//@Entity
//@Table(name="BOARD")
//
//@SequenceGenerator(
//	name = "BOARD_SEQ_GENERATOR",
//	sequenceName = "BOARD_SEQUENCE",
//	initialValue = 1,
//	allocationSize = 1
//)
public class Board4 {
	@Id
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "BOARD_SEQ_GENERATOR"
	)
	private Long seq;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String writer;
	
	@Column(nullable = false)
	private String content;
	
	@Column(columnDefinition = "INTEGER DEFAULT 0")
	private Long cnt;

//	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date insert_ts;
	
	private Date update_ts;
	
	
   
} // end class
