package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


@Data

@Entity
@Table(name="board7")
@SequenceGenerator(
		name = "SequenceGenerator",
		sequenceName = "board_seq",
		initialValue = 1,
		allocationSize = 1
	)
public class BoardWithSequenceGenerator implements Serializable {
	@Serial private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceGenerator")
	private Long seq;
	
	@Basic private String title;
	@Basic private String writer;
	@Basic private String content;
	
	@Basic
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@Basic
	@UpdateTimestamp
	private Date updateDate;
	
	@Basic private Long cnt;
	
	
   
} // end class


