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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


@Data

@Entity
@Table(name="board8")
@TableGenerator(
		name="TableGenerator",
		table="tbl_idgen",
		pkColumnName="genkey",
		valueColumnName="genvalue",
		pkColumnValue="seq",
        allocationSize=1
	)
public class BoardWithTableGenerator implements Serializable {
	@Serial private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TableGenerator")
	private Long seq;
	
	@Basic private String title;
	@Basic private String writer;
	@Basic private String content;

	// Important: auto timestamp created.
	@CreationTimestamp
	@Basic private LocalDateTime createDate;

	// Important: auto timestamp is NOT created.
//	@Temporal(TemporalType.DATE)					// Only date, NOT time
//	@Temporal(TemporalType.TIMESTAMP)		// Both date and time
	// Important: auto timestamp created.
	@UpdateTimestamp
	@Basic private Date updateDate;
	
	@Basic private Long cnt;
	
	
   
} // end class


