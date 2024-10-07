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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.Data;


@Data

@Entity
@Table(name = "board6", schema = "public", uniqueConstraints = {
//		@UniqueConstraint(name = "board5_seq_writer_uk", columnNames = { "seq", "writer" } ),		// With composite key
		@UniqueConstraint(name = "board6_createDate_uk", columnNames = { "createDate" } )			// With single key
}, indexes = {
//		@Index(name = "board6_title_idx", columnList = "title", unique = false),									// With single column
//		@Index(name = "board6_content_idx", columnList = "content", unique = false),						// With single column
		@Index(name = "board6_title_content_idx", columnList = "title, content", unique = false)		// With composite columns
})
public class Board6 implements Serializable {
	@Serial private static final long serialVersionUID = 1L;

	// H2 database uses a SEQUENCE which generate a value of the primary key.
	@Id
	
	/* 1st. method: With @GeneratedValue for making an unique id */
//	@GeneratedValue
	@GeneratedValue(strategy = GenerationType.AUTO)	// (1) AUTO (default) (2) IDENTITY (3) SEQUENCE (4) TABLE

	/* 2nd. method: With @TableGenerator & @GeneratedValue for making an unique id */
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
	
	/*
	 * --------------------
	 * @Temporal
	 * --------------------
	 * Important: the persistent field or property for a `Temporal type` must be of
	 * 		(1) type java.util.Date
	 * 		(2) java.util.Calendar
	 * 		(3) java.util.GregorianCalendar
	 */
	
//	@Temporal(TemporalType.DATE)			// XX
	@Column(name = "createDate", nullable = false, insertable = true, updatable = false, unique = true)
	private LocalDateTime createDate;

	/*
	 * --------------------
	 * @Temporal
	 * -------------------
	 * 1st. case : If NO `@Temporal` annotation attached, then it equals `@Temporal(TemporalType.TIMESTAMP)`.
	 * 2nd.case: @Temporal(TemporalType.DATE) have NO time, but only date.
	 * 3rd. case: @Temporal(TemporalType.TIME) equals `@Temporal(TemporalType.TIMESTAMP)`.
	 * 4th. case: @Temporal(TemporalType.TIMESTAMP) have both date and time.
	 */	
	@Temporal(TemporalType.DATE)					// 2nd.case: NO time
//	@Temporal(TemporalType.TIME)					// 3rd. case: Both date and time 
//	@Temporal(TemporalType.TIMESTAMP)		// 4th. case == 3rd. case
	
	@Column(name = "updateDate", insertable = false, updatable = true)
	private Date updateDate;
	
	// `insertable = false` means that this column is NOT included in column definition part of the INSERT statement
	@Column(name = "cnt", columnDefinition = "int default 0", insertable = false)
	private Long cnt = 0L;		// Default: 0
	
	// NO mapped persistent field to the table.
	@Transient
	private String notPersistentField;
	
	/* New appended persistent field */
	
	// If the property `hibernate.hbm2ddl.auto = update` in the `persistence.xml` file,
	// the previous appended column do NOT be deleted automatically when updating.
	// Thus, the age column of the table mapped with the below persistent field remains continuously.

//	@Column(name = "age", columnDefinition = "int default 23", nullable = false, insertable = false)
//	private Integer age;			// 1st. appended
	
//	------
	@Column(name = "myAge", columnDefinition = "int default 23", nullable = false, insertable = false)
	private Integer myAge;		// 2nd. appended, at this time 1st. appended field should be removed.
   
	
} // end class


