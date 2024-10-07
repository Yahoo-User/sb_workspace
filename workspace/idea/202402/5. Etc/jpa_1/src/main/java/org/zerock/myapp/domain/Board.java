package org.zerock.myapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data

// Entity implementation class for Entity: Board
@Entity
@Table(name = "tbl_board")
public class Board implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;
	
	private String title;
	private String writer;
	private String content;
//	private Date createDate;
	private Date createDate = new Date();
	private Long cnt = 0L;
	
	
   
} // end class
