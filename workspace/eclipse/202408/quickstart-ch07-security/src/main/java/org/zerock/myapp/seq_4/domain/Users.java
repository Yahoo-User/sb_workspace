package org.zerock.myapp.seq_4.domain;

import java.io.Serial;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper=false)
@Data

/**
 * -------------------------------
 * Hibernate Annotatios for Generating Dynamic SQLs
 * -------------------------------
 * NOT standard JPA.
 * Default JPA generates Prepared SQLs.
 * 	If using the below annotations, JPA generates Dynamic SQLs.
 * 
 * 	@DynamicInsert 					// Dynamic SQLs auto-generation with `only non-null` columns
 * 	@DynamicUpdate					// Dynamic SQLs auto-generation with `only changed` columns
 */

@Entity(name = "Users")
@Table(name = "USERS")
public class Users extends JpaAudit {		// Parent, One (1)
	@Serial private static final long serialVersionUID = 1L;

	// -----------------------------
	// 1. Set PK
	// -----------------------------
	@Column(updatable = false, nullable = false, unique = true)	// PK = Unique + Not Null + Not Updatable
	@Id protected String username;


	// -----------------------------
	// 2. Additional Fields
	// -----------------------------
	@Basic(optional = false)
	private String password;
	
	// (1) If H2, the column: enabled boolean not null.
	//	   thus, check contraint Not necessary.
	// (2) If Oracle, the column : enabled number(1,0) not null check (enabled in (0,1)). 
	//	   thus, check contraint Not necessary.
	@Basic(optional = false)
	private Boolean enabled;
	
	

} // end class
