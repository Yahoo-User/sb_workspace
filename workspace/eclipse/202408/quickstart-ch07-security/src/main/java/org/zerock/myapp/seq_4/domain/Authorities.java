package org.zerock.myapp.seq_4.domain;

import java.io.Serial;

import org.hibernate.annotations.Check;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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

//Specifies a composite primary key class that is mapped to multiple fields or properties of the entity. 
@IdClass(AuthoritiesKey.class)

@Entity(name = "Authorities")
@Table(name = "AUTHORITIES")
public class Authorities extends JpaAudit {	// Child, Many (N)
	@Serial private static final long serialVersionUID = 1L;

	// -----------------------------
	// 1. Set PK
	// -----------------------------
	@Column(updatable = false, nullable = false)	// PK = Unique + Not Null + Not Updatable
	@Id protected String username;
	

	// -----------------------------
	// 2. Additional Identifiers
	// -----------------------------
	@Check(name = "ck_authority", constraints = "authority IN ('USER', 'ADMIN')")
	
	@Column(updatable = false, nullable = false)	// PK = Unique + Not Null + Not Updatable
	@Id private String authority;
	
	

} // end class
