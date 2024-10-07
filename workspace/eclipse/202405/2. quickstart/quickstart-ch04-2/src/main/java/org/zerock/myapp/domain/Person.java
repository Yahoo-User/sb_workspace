package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.zerock.myapp.listener.EntityLifecyleListener;

import lombok.Data;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Data

/**
 * -------------------------------
 * Hibernate Annotatios for Generating Dynamic SQLs
 * -------------------------------
 * NOT standard JPA.
 * Default JPA generates Prepared SQLs.
 * 	If using the below annotations, JPA generates Dynamic SQLs.
 * 
 * 	@DynamicInsert 				// Dynamic SQLs auto-generation with `only non-null` columns
 * 	@DynamicUpdate				// Dynamic SQLs auto-generation with `only changed` columns
 */

@EntityListeners(EntityLifecyleListener.class)

@Entity(name="Person")
@Table(name="PERSON")
public class Person implements Serializable {		// an Entity
	@Serial private static final long serialVersionUID = 1L;

	// --------------------
	// 1. Set PK
	// --------------------
	@Id
	@GeneratedValue													// PK, Auto-generated.
	@Column(name = "person_id")							// PK, Manual
	private Long id;
	
	// --------------------
	// 2. It's own fields
	// --------------------
	@Basic(optional = false)										// Not Null Constraint
	private String name;
	
	@Basic(optional = false)										// Not Null Constraint
	private Integer age;


	// ==============================================
	// The callback methods on each Entity's lifecycle events
	// ==============================================
	
	@PrePersist
	void prePersist() {		// Transient
		log.trace("prePersist({}) invoked.", this);
	} // prePersist
	
	@PostPersist
	void postPersist() {		// Transient -> Managed
		log.trace("postPersist({}) invoked.", this);
	} //postPersist
	
	@PreRemove
	void preRemove() {		// Before Managed -> Removed
		log.trace("preRemove({}) invoked.", this);
	} // preRemove
	
	@PostRemove
	void postRemove() {	// After Managed -> Removed
		log.trace("postRemove({}) invoked.", this);
	} // postRemove
	
	@PreUpdate
	void preUpdate() {		// Before Managed -> Merged 
		log.trace("preUpdate({}) invoked.", this);
	} // preUpdate
	
	@PostUpdate
	void postUpdate() {	// After Managed -> Merged
		log.trace("postUpdate({}) invoked.", this);
	} // postUpdate
	
	@PostLoad
	void postLoad() {		// DB -> Managed (by finding)
		log.trace("postLoad({}) invoked.", this);
	} // postLoad
	
} // end class

