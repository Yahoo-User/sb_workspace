package org.zerock.myapp.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;


@Data

@Entity
@Table(name="BOARD")
public class Board {
	@Id
	@GeneratedValue		// == @GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;
	
	private String title;
	private String writer;
	private String content;
	private Long cnt;

//	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date insert_ts;
	
	private Date update_ts;
	
	
   
} // end class
