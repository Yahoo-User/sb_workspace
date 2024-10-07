package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;


@Data

@Entity
@Table(name = "board5", schema = "public", uniqueConstraints = {
//		@UniqueConstraint(name = "board5_seq_writer_uk", columnNames = { "seq", "writer" } ),		// With composite key
		@UniqueConstraint(name = "board5_createDate_uk", columnNames = { "createDate" } )			// With single key
}, indexes = {
//		@Index(name = "board5_title_idx", columnList = "title", unique = false),									// With single column
//		@Index(name = "board5_content_idx", columnList = "content", unique = false),						// With single column
		@Index(name = "board5_title_content_idx", columnList = "title, content", unique = false)		// With composite columns
})
public class Board5 implements Serializable {
	@Serial private static final long serialVersionUID = 1L;

	// H2 database uses a SEQUENCE which generate a value of the primary key.
	@Id
	
	/* 1st. method: With @GeneratedValue for making an unique id */
//	@GeneratedValue
	@GeneratedValue(strategy = GenerationType.AUTO)	// (1) AUTO (default) (2) IDENTITY (3) SEQUENCE (4) TABLE

	/* 2st. method: With @TableGenerator & @GeneratedValue for making an unique id */
//	@TableGenerator(name="seqGen", table="ID_GEN", pkColumnName="GEN_KEY", valueColumnName="GEN_VALUE", pkColumnValue="SEQ_ID", allocationSize=1)
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGen")
	private Long seq;
	
	@Column
//	@Column(table = "board5")
	private String title;
	
	@Column(name = "writer")
	private String writer;
	
	@Column(name = "content", insertable = true, updatable = true, length = 2000)
	private String content;
	
	@Column(name = "createDate", nullable = false, insertable = true, updatable = false, unique = true)
	private LocalDateTime createDate;

	@Column(name = "updateDate", insertable = false, updatable = true)
	private Date updateDate;
	
	// `insertable = false` means that this column is NOT included in column definition part of the INSERT statement
	@Column(name = "cnt", columnDefinition = "int default 0", insertable = false)
	private Long cnt = 0L;		// Default: 0
	
	
   
} // end class


