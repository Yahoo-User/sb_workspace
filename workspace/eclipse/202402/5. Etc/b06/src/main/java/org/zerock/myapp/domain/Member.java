package org.zerock.myapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data

@Entity
@Table(name = "tbl_member")
public class Member {
	
	@Id
	@Column(name="userid", nullable = false, updatable = false)
	private String id;
	
	@Column(name = "passwd", nullable = false)
	private String pw;
	
	private String name;
	private String role;

} // end class
