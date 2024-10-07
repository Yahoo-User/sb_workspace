package org.zerock.myapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;


@Data

//@Entity
//@Table(
//	name = "BOARD"
//	
////	, uniqueConstraints = {
////		@UniqueConstraint(columnNames = { "title", "writer" })
////	}
//)
public class Board2 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String writer;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false, columnDefinition = "int default 0")
	private Long cnt;

//	@Column(columnDefinition = "date default current_timestamp")	// in H2, Oracle
//	@Column(nullable = false, columnDefinition = "timestamp default current_timestamp")	// in H2, Oracle
	
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date write_date;		// 작성일시
	
	@Transient
	private String searchCondition;	// Mapping 에서 제외
	
	@Transient
	private String searchKeyword;	// Mapping 에서 제외

	@CreationTimestamp				// OK: Auto-Creation of Timestamp Value.
	private Date insert_ts;

//	@UpdateTimestamp				// OK: Auto-Updated by Timestamp Value.
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_ts;

	
   
} // end class
