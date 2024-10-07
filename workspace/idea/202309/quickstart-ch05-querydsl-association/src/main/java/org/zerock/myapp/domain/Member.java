package org.zerock.myapp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@ToString(exclude="boardList")

@Data

@Entity
@Table(name = "member")
public class Member {
	@Id
	@Column(name="member_id")
	private String id;
	
	private String password;
	private String name;
	private String role;


	// @JoinColumn
	// org.hibernate.AnnotationException:
	// Association 'org.zerock.myapp.domain.Member.boardList' is 'mappedBy' another entity and may not specify the '@JoinColumn'

	@OneToMany(
		// (Optional) The entity class that is the target of the association.
		// Optional only if the collection property is defined using Java generics. Must be specified otherwise.
		// Defaults to the parameterized type of the collection when defined using generics.
		targetEntity = Board.class,

		// The field that owns the relationship.
		// Required unless the relationship is `unidirectional`.
		mappedBy = "member",

		// (Optional) The operations that must be cascaded to the target of the association.
		// Defaults to no operations being cascaded.
		// When the target collection is a `java.util.Map`, the cascade element applies to the map value.
		cascade = CascadeType.ALL,

		// (Optional) Whether the association should be lazily loaded or must be eagerly fetched.
		// The `EAGER` strategy is a requirement on the persistence provider runtime that the associated entities must be eagerly fetched.
		// The `LAZY` strategy is a hint to the persistence provider runtime.
		fetch = FetchType.EAGER
	)
	private List<Board> boardList = new ArrayList<>();
	
	
	
} // end class
