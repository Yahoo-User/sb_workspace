package org.zerock.myapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


//@Log4j2
@Data

@Entity
@Table(name="MEMBER")
public class Member {
	
	@Id
	@Column(insertable = true, nullable = false, unique = true, updatable = false)
	private String id;
	
	@Column(nullable = false)
	private String password;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	private String enabled;
	

} // end class
