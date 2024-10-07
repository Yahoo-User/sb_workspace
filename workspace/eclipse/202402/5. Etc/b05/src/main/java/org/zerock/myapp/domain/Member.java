package org.zerock.myapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;


@ToString(exclude="boardList")
@Data

@Entity(name="Member")
@Table(name="tbl_member")
public class Member {
	
	@Id
	@Column(name="member_id", nullable=false, length=50)
	private String id;
	
	@Column(name="member_pw", nullable=false, length=50) private String password;
	@Column(name="full_name", nullable=false, length=50) private String name;
	@Column(name="role", nullable=false, length=50) private String role;
	
	
	@OneToMany(

		//---------------------------------------------------------------------//
		// (Optional) The `entity class` that is the `target` of the association.
		// 	`Optional only` if the collection property is defined using Java `generic`.
		// 	`Must` be specified otherwise.
		// 	Defaults to the parameterized type of the collection when defined using `generic`.
		//---------------------------------------------------------------------//
		targetEntity=Board.class,

		//---------------------------------------------------------------------//
		// (Optional) The operations that must be cascaded to the target of the association. 
		// Defaults to no operations being cascaded. 
		//---------------------------------------------------------------------//
//		cascade= {},					// Default
		cascade= CascadeType.ALL,		// For `persistence transition`
		
		// (Optional) Whether the association should be lazily loaded or must be eagerly fetched.
		//   a. The `EAGER` strategy is a requirement on the persistence provider runtime
		//      that the associated entities must be eagerly fetched.
		//   b. The LAZY strategy is a hint to the persistence provider runtime.
		fetch= FetchType.LAZY,		// Default
//		fetch= FetchType.EAGER,

		//---------------------------------------------------------------------//
		// The field that `owns` the relationship.
		// `Required` unless the relationship is `unidirectional`. (***)
		//---------------------------------------------------------------------//
		mappedBy = "writer"
	)
	private List<Board> boardList = new ArrayList<>();

} // end class
