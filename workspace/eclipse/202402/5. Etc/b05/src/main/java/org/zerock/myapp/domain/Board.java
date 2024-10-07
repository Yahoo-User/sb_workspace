package org.zerock.myapp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.ToString;


@ToString(exclude= "writer")
@Data

@Entity(name="Board")
@Table(name="tbl_board")
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long seq;
	
	@Column(name = "title", length = 255, nullable = false)	private String title;
	
	
	// ----------------------------------- //
	// replaced with JPA association
	// ----------------------------------- //
	
//	@Column(name = "writer", length = 50, nullable = false)	private String writer;
	
	// 1 (Board) : 1 (Member)
	@ManyToOne(
			
		//---------------------------------------------------------------------//
		// (Optional) Whether the `association` is `optional`.
		// If set to `false` then a `non-null` relationship must always exist.
		//---------------------------------------------------------------------//
//		optional = false,	// If `FK` column non null expected, it's good to set `false` explicitly (***)
							// If set to `true` (default) -> `FK` column value can be null.
							// If set to `false`,         -> `FK` column value cannot be null.

		//---------------------------------------------------------------------//
		// (Optional) Whether the association should be lazily loaded or must be eagerly fetched.
		// a. The `EAGER` strategy is a requirement on the persistence provider runtime
		//    that the associated entity must be eagerly fetched.
		// b. The `LAZY` strategy is a hint to the persistence provider runtime.
		//---------------------------------------------------------------------//
//		fetch = FetchType.EAGER,	// (Default)
//		fetch = FetchType.LAZY,
		
		//---------------------------------------------------------------------//
		// 주의사항 (***)
		//---------------------------------------------------------------------//
		// 이 어노테이션 속성을 아래와 같이 지정하면, `N` cardinality 에 속한
		// 객체가 DB에 저장될 때, 연쇄적으로 `1` cardinality에 속한 객체까지 저장 시도
		// 이로 인해서, `1` cardinality 에 속한 객체의 키가 중복되어 오류발생가능.
		// 따라서, 이 어노테이션 속성은 기본값 설정({})이 좋음.
		//---------------------------------------------------------------------//
		// (Optional) The operations that must be cascaded to the target of the association. 
		// By default no operations are cascaded.
		//---------------------------------------------------------------------//
		// if `CascadeType.PERSIST` sets, when `N` cardinality object be saved,
		// at the same time, `1` cardinality entity also try to be saved. (***)
		//---------------------------------------------------------------------//
//		cascade = {},					// Default

		//---------------------------------------------------------------------//
		// (Optional) The entity class that is the target of the association. 
		// Defaults to the type of the field or property that stores the association. 
		//---------------------------------------------------------------------//
//		targetEntity = Member.class		// Mandatory, It is good to set target entity explicitly.
	)
	@JoinColumn(
		name="writer",						// Mandatory : define the table column name. (***)
//		referencedColumnName="member_id",	// Mandatory : define the column name of the referenced table. (***)
		nullable=false
//		updatable=true
	)
	private Member writer;	// If @JoinColumn::name attribute omitted, (***)
							//    field name is `writer',
							//    real column name: `writer_member_id`,
							// That is, the final table column name == ( `field name` + `_` + `referenced column name` )
	
	// ----------------------------------- //
	
	@Column(name = "content", length = 1000, nullable = false) private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insert_ts", nullable = false)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_ts", nullable = true)
	private Date updateDate;
	
	@Column(name = "cnt", nullable = false)
	private Long cnt;
	
	
	// For `persistence transition`
	public void setWriter(Member writer) {
		this.writer = writer;
		
		this.writer.getBoardList().add(this);
	} // setWriter
	
} // end class
