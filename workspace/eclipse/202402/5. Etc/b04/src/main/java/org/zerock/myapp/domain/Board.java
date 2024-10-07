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

import lombok.Data;


@Data

/**
 * Entity implementation class for Entity: Board
 *
 */
@Entity

@Table(name="BOARD")
//@Table(name="tbl_board", uniqueConstraints= {
//	@UniqueConstraint(columnNames= {"seq", "writer"})
//})
public class Board implements Serializable {
	private static final long serialVersionUID = 1L;


//	===============================================
	
	// Indicates that the persistence provider should pick an appropriate strategy for the particular database.
	
	@GeneratedValue(strategy=GenerationType.AUTO)		// OK : Default strategy.
	
//	===============================================
	
	// Indicates that the persistence provider must assign primary keys
	// for the entity using a database identity column.
	
	// org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException:
	// NULL not allowed for column "SEQ"; SQL statement:
	// insert into BOARD (seq, cnt, content, createDate, title, writer) values (`null`, ?, ?, ?, ?, ?) [23502-206]
//	@GeneratedValue(strategy=GenerationType.IDENTITY)	// XX
//	===============================================

	// ---------------------------------------------
	// Defines a primary key generator that may be referenced by name
	// when a generator element is specified for the `@GeneratedValue` annotation.
	//
	// a. name : (Required) A unique generator name that can be referenced by one or more classes
	//            to be the generator for `primary key` values.
	// b. sequenceName: (Optional) The name of the database sequence object from which to obtain primary key values.
    //                   Defaults to a provider-chosen value.
	// c. initialValue : (Optional) The value from which the sequence object is to start generating.
	// d. allocationSize : (Optional) The amount to increment by when allocating sequence numbers from the sequence.
	//---------------------------------------------
//	@SequenceGenerator(
//		name="BOARD_SEQ_GENERATOR",
//		sequenceName="BOARD_SEQUENCE",
//		initialValue=10,
//		allocationSize=10
//	)
	
	// Indicates that the persistence provider must assign primary keys
	// for the entity using a `database sequence`.
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOARD_SEQ_GENERATOR")	// OK
	
//	===============================================

	// ---------------------------------------------
	// Defines a primary key generator that may be referenced by name when a generator element is specified 
	// for the `@GeneratedValue` annotation.
	//
	// a. name : (Required) A unique generator name that can be referenced by one or more classes
	//            to be the generator for `id` values.
	// b. table: (Optional) Name of table that stores the generated id values.
	//            Defaults to a name chosen by persistence provider.
	// c. pkColumnValue : (Optional) The primary key value in the generator table 
	//                     that distinguishes this set of generated values from others that may be stored in the table.
	//                     Defaults to a provider-chosen value to store in the primary key column of the generator table
	// d. initialValue : (Optional) The initial value to be used to initialize the column
	//                    that stores the last value generated.
	// e. allocationSize : (Optional) The amount to increment by when allocating id numbers from the generator.
	//---------------------------------------------
//	@TableGenerator(
//		name="BOARD_SEQ_GENERATOR",
//		table="all_sequences",
//		pkColumnValue="board_seq",
//		initialValue=0,
//		allocationSize=3
//	)

	//---------------------------------------------
	
	// Indicates that the persistence provider must assign primary keys
	// for the entity using an underlying database table to ensure uniqueness.
	
	// org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: Unique index or primary key violation:
	// "PRIMARY KEY ON PUBLIC.BOARD(SEQ) ( /* key:1 */ CAST(1 AS BIGINT), CAST(0 AS BIGINT), 'CONTENT_1', TIMESTAMP '2022-01-15 08:55:24.565111', 'TITLE_1', 'WRITER_1')"; SQL statement:
	// insert into BOARD (cnt, content, createDate, title, writer, seq) values (?, ?, ?, ?, ?, ?) [23505-206]
//	@GeneratedValue(strategy=GenerationType.TABLE, generator="BOARD_SEQ_GENERATOR")		// OK
	
//	===============================================
	
	@Id private Long seq;
	
	
	@Column(length=500, nullable=false, name="title") private String title;
	@Column(length=100, nullable=false, name="writer") private String writer;
	@Column(length=2000, nullable=false, name="content") private String content;
	
//	@Temporal(TemporalType.DATE)			// inserted ONLY Date
//	@Temporal(TemporalType.TIME)			// inserted Date and Time, but precision abbreviated.
	@Temporal(TemporalType.TIMESTAMP)		// Default, inserted Date and Time
	@Column(nullable=false, unique=true, name="createDate")
	private Date createDate;
	
	@Column(nullable=false, unique=false, name="cnt") private Long cnt;
	
//	--------------------------------
	
	@Transient private Integer searchCondition;
	@Transient private String searchKeyword;
	

} // end class
