package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.zerock.myapp.common.CommonEntityLifecycleListener;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;


//@Log4j2

// Because of a super class (org.zerock.myapp.common.CommonEntityCallbacks)
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
 * 	@DynamicUpdate				// Dynamic SQLs auto-generation with `only changed` columns
 */

@EntityListeners(CommonEntityLifecycleListener.class)

@Entity(name = "Board")
@Table(name = "BOARD")
@SequenceGenerator(name = "board_seq", allocationSize = 1)
public class Board implements Serializable {
	@Serial private static final long serialVersionUID = 1L;

	// 1. Set PK, Auto-Generation.
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq")
	@Column(name = "board_id", updatable = false)	// Not Null Constraint, Not Updatable.
	private Long seq;
	
	
	// 2. Additional Fields.
	@Column(nullable = false)											// Not Null Constraint, Updatable.
	private String title;
	
	@Column(nullable = false, updatable = false)			// Not Null Constraint, Not Updatable.
	private String writer;
	
	private String content;												// Null Constraint, Updatable.

	@Column(nullable = false)											// Not Null Constraint, Updatable.
	private int cnt = 0;
	
	
	// 3. JPA Auditing.
	@CurrentTimestamp(event = EventType.INSERT)			// Auto-Generation
	@CreatedDate																	// Jpa Audit
	@Temporal(TemporalType.TIMESTAMP)							// Including Date & Time
	@Column(nullable = false, updatable = false)				// Not Null Constraint, Not Updatable.
	private Date createDate;
	
	@CurrentTimestamp(event = EventType.UPDATE)		// Auto-Generation
	@LastModifiedDate															// Jpa Audit
	@Temporal(TemporalType.TIMESTAMP)							// Including Date & Time
	@Basic(optional = true)													// Null Constraint, Updatable.
	private Date updateDate;
	
	
/**
  * ----------------------------------
  * 4. Entity Lifecyle Annotations
  * ----------------------------------
  * *Important: Callback methods annotated on the bean class must (1) return void (2) take no arguments
	@PrePersist 		void prePersist() 	{ log.debug("(C-1) prePersist() invoked."); }
	@PostPersist		void postPersist() 	{ log.debug("(C-2) prePersist() invoked."); }
	@PostLoad 		void postLoad() 		{ log.debug("(R) postLoad() invoked."); }
	@PreUpdate		void preUpdate() 	{ log.debug("(U-1) preUpdate() invoked."); }
	@PostUpdate		void postUpdate() 	{ log.debug("(U-2) postUpdate() invoked."); }
	@PreRemove		void preRemove() 	{ log.debug("(D-1) preRemove() invoked."); }
	@PostRemove	void postRemove() { log.debug("(D-2) postRemove() invoked."); }
  */
	
	
	
} // end class


