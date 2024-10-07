package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;


@Data

@Entity
@Table(name = "board2")
public class Board2 implements Serializable {
	@Serial private static final long serialVersionUID = 1L;

	@Id

	// H2 database uses a SEQUENCE which generate a value of the primary key.
	
	/* 1st. method: With @GeneratedValue for making an unique id */
//	@GeneratedValue
//	@GeneratedValue(strategy = GenerationType.AUTO)	// (1) AUTO (default) (2) IDENTITY (3) SEQUENCE (4) TABLE

	/* 2st. method: With @TableGenerator & @GeneratedValue for making an unique id */
	@TableGenerator(name="seqGen", table="ID_GEN", pkColumnName="GEN_KEY", valueColumnName="GEN_VALUE", pkColumnValue="SEQ_ID", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGen")
	private Long seq;
	
	private String title;
	private String writer;
	private String content;
	private LocalDateTime createDate;
	private Date updateDate;
	private Long cnt;
	
	
   
} // end class


