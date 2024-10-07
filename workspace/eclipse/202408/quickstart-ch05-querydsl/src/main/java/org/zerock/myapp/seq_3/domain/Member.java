package org.zerock.myapp.seq_3.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;
import org.zerock.myapp.listener.CommonEntityLifecycleListener;
import org.zerock.myapp.seq_1.domain.Board;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;


//@Log4j2
@Data

@EntityListeners(CommonEntityLifecycleListener.class)

@Entity(name = "Member")
@Table(name = "MEMBER")
public class Member implements Serializable {		// Parent, One (1)
	@Serial private static final long serialVersionUID = 1L;
	
	// 1. Set PK, Manually.
	@Id
	@Column(name = "member_id")
	private Long id;

	
	// 2. Additional Fields.
	@Basic(optional = false)
	private String password;
	
	private String name;
	private String role;
	
	
	// 3. JPA Auditing.
//	@Temporal(TemporalType.TIMESTAMP)
//	@CreatedDate
	@CurrentTimestamp(event = EventType.INSERT)
	@Basic(optional = false)
	private LocalDateTime createDate;

//	@Temporal(TemporalType.TIMESTAMP)
//	@LastModifiedDate
	@CurrentTimestamp(event = EventType.UPDATE)
	private LocalDateTime updateDate;
	
	
	// ======================================
	// Mapping OneToMany ( 1 : N ), *Bi-Directional Association
	// ======================================
	@ToString.Exclude
	
//	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER)				// 1
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER, targetEntity = Board.class, cascade = CascadeType.ALL)		// 2
	
	private List<Board> boards = new ArrayList<>();											// Set *FK "field name" of the Child.
	

/**
	public boolean addBoard(Board board) {
		log.trace("addBoard({}) invoked.", board);
		
		if(board == null) return false;
		else {
			board.setMember(this);						// Set FK value
			return this.boards.add(board);
		} // if-else
	} // addBoard
*/
	
} // end class
