package org.zerock.myapp.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Log4j2
@ToString(exclude="member")
@Data

@Entity(name="Board")
public class Board {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	private String title;
//	private String writer;
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	
	private Integer cnt = 0;
	
	@ManyToOne(
		targetEntity = Member.class,
		fetch = FetchType.EAGER,
		optional = false
	)
	@JoinColumn(
		// The name of the foreign key column.
		name ="member_id",
		nullable = true
	)
	private Member member;
	
	
	public void setMember(Member member) {
		log.trace("setMember({}) invoked.", member);
		
		this.member = member;
		member.getBoardList().add(this);		// ***
	} // setMember
	
	

} // end class
