package org.zerock.myapp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data


@Entity(name="Board")
@Table(name="tbl_board")
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long seq;
	
	private String title;
	
	@Column(updatable = false)
	private String writer;
	
	private String content;
	private Long cnt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insert_ts", updatable = false)
	private Date createDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")		// (***)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_ts")
	private Date updateDate;
	

} // end class
