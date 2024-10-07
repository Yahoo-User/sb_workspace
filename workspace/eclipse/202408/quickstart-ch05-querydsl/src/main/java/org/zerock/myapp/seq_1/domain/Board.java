package org.zerock.myapp.seq_1.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;
import org.zerock.myapp.listener.CommonEntityLifecycleListener;
import org.zerock.myapp.seq_3.domain.Member;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Data

@EntityListeners(CommonEntityLifecycleListener.class)

@Entity(name = "Board")
@Table(name = "BOARD")
public class Board implements Serializable {		// Child, Many (N)
	@Serial private static final long serialVersionUID = 1L;
	
	// 1. Set PK, Manually
	@Id
//	@SequenceGenerator(name = "seq_board", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_board")
	@Column(name = "board_id")
	private Long seq;
	
	// 2. Additional Fields
	private String title;
	private String content;
	private String writer;
	
	@Column(columnDefinition = "INTEGER default 0")
	private int cnt;
	
	// 3. JPA auditing fields
//	@CreatedDate
//	@Temporal(TemporalType.TIMESTAMP)
	@CurrentTimestamp(event = EventType.INSERT)
	@Basic(optional = false)
	private LocalDateTime createdDate;

//	@LastModifiedDate
//	@Temporal(TemporalType.TIMESTAMP)
	@CurrentTimestamp(event = EventType.UPDATE)
	@Basic(optional = true)
	private LocalDateTime updateDate;
	

	// ======================================
	// Mapping ManyToOne ( N : 1 ), *Uni-Directional Association
	// ======================================
	@ToString.Exclude

	/**
	 * (Optional) Whether the association is optional.
	 * If set to `false` then a `non-null` relationship must always exist.
	 * --------------------
	 * if optional = true,		(default) : LEFT OUTER JOIN
	 * if optional = false,		INNER JOIN
	 * --------------------
	 */
//	@ManyToOne					// 1
	@ManyToOne(targetEntity = Member.class, cascade = CascadeType.ALL)		// 2

	/**
	 * (Optional) Whether the `foreign key` column is `nullable`. 
	 * --------------------
	 * if nullable = true,		(default) : LEFT OUTER JOIN
	 * if nullable = false,	INNER JOIN
	 * --------------------
	 */
	@JoinColumn(name = "member_fk")			// 1
//	@JoinColumn(													// 2
//			name = "member_fk", 
//			referencedColumnName = "member_id", 
//			table = "Member", 
//			unique = false)
	
	@Basic(optional = true)
	private Member member;		// Parent : Mapping FK column of the Child table for the Parent table.


	public boolean setMember(Member member) {
		log.trace("setMember({}) invoked.", member);
		
		if(member == null) return false;
		else {
			this.member = member;
			return this.member.getBoards().add(this);
		} // if-else
	}	// setMember
	
	
} // end class

