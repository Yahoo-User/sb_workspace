package org.zerock.myapp.domain;


import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

//import javax.persistence.*;				// XX : Spring Boot v2.7.14
import jakarta.persistence.*;				// OK : Spring Boot v3.1.2

import java.util.Date;


@Log4j2
@ToString(exclude="member")

@Data

@Entity(name="Board")
@Table(name = "board")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;
	
	private String title;
	private String content;

	// For `N : 1` Association Mapping Presentation, This abbreviated.
//	private String writer;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	
	private Long cnt = 0L;

	@JoinColumn(
		// (Optional) The name of the foreign key column.
		// The table in which it is found depends upon the context.
		name ="member_id",

		// (Optional) Whether the foreign key column is nullable.
		nullable = true
	)
	@ManyToOne(
		// (Optional) The entity class that is the `target of the association`.
		// Defaults to the type of the field or property that stores the association.
		targetEntity = Member.class,

		// (Optional) Whether the association should be `lazily` loaded or must be `eagerly` fetched.
		// The `EAGER` strategy is a requirement on the persistence provider runtime that the associated entity must be eagerly fetched.
		// The `LAZY` strategy is a hint to the persistence provider runtime.
		fetch = FetchType.EAGER,

		// (Optional) Whether the association is `optional`.
		// If set to `false` then a `non-null` relationship must `always` exist.
		optional = false
	)
	private Member member;
	
	
	public void setMember(Member member) {
		log.trace("setMember({}) invoked.", member);

		this.member = member;
		member.getBoardList().add(this);		// ***
	} // setMember
	
	

} // end class
