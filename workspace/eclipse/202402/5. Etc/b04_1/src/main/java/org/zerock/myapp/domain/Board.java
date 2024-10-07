package org.zerock.myapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.Data;


@Data

@Entity
@Table(name="TBL_BOARD", uniqueConstraints= {@UniqueConstraint(columnNames= {"SEQ"})})
public class Board implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	// Default
	@Column(name="SEQ", nullable=false, unique=true)
	private Long seq;
	
	
	@Column(name="TITLE", nullable=false, length=500) private String title;
	@Column(name="WRITER", nullable=false, length=100) private String writer;
	@Column(name="CONTENT", nullable=false, length=2000) private String content;
	
	@Temporal(TemporalType.TIMESTAMP)   // Default : Date & Time
	@Column(name="INSERT_TS", nullable=false) private Date createDate;
	
	@Column(name="COUNT", nullable=false) private Long cnt;
	
	
	@Transient private Integer searchCondition;
	@Transient private String  searchKeyword;
   
	
	
} // end class
