package org.zerock.myapp.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;


@ToString(exclude="boardList")
@Data

@Entity
public class Member {
	@Id
	@Column(name="MEMBER_ID")
	private String id;
	
	private String password;
	private String name;
	private String role;
	
	@OneToMany(
		targetEntity = Board.class,
		mappedBy = "member",
		cascade=CascadeType.ALL,
		fetch = FetchType.EAGER
	)
	// org.hibernate.AnnotationException:
	// 		Association 'org.zerock.myapp.domain.Member.boardList' is 'mappedBy' another entity and may not specify the '@JoinColumn'
//	@JoinColumn
	private List<Board> boardList = new ArrayList<>();
	
	
	
} // end class
