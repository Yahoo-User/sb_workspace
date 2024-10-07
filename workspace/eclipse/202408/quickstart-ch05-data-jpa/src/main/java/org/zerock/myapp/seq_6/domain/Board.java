package org.zerock.myapp.seq_6.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.generator.EventType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.zerock.myapp.seq_7.jpa.listener.CommonEntityLifecycleListener;

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


@Data

@EntityListeners(CommonEntityLifecycleListener.class)

@Entity(name = "Board")
@Table(name = "BOARD")
public class Board implements Serializable {				// Entity
	@Serial private static final long serialVersionUID = 1L;
	
	// 1. Set PK
	@Id
	@SequenceGenerator(name = "seq_board", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_board")
	@Column(name = "board_id")
	private Long seq;
	
	// 2. Additional Fields
	@Basic(optional = false)
	private String title;
	
	@Basic(optional = false)
	private String writer;
	
	@Basic(optional = true)
	private String content;
	
	@Column(columnDefinition = "integer default 0")
	private int cnt;


	// ---------------
	// 1st. method with the Hibernate JPA provider	- OK
	// ---------------
//	@CreationTimestamp														// 1
//	@CreationTimestamp(source = SourceType.VM)			// 2, Default

	// ---------------
	// 2nd. method with the Hibernate JPA provider - OK, *BEST
	// ---------------
//	@CurrentTimestamp														// 1
//	@CurrentTimestamp(source = SourceType.DB)				// 2, Default
//	@CurrentTimestamp(event =  {EventType.INSERT})		// 3, Default, event =  {EventType.INSERT, EventType.UPDATE}
//	@CurrentTimestamp(timing)											// 4, `timing` element is @Deprecated(since="6.2")
	
	// 5, *BEST, Only When Updating(event), Set DB Timestamp(source)
	@CurrentTimestamp(event = EventType.INSERT, source = SourceType.DB)

	@Temporal(TemporalType.TIMESTAMP)							// For seting Date & Time	 in the Standard JPA
	@CreatedDate																	// For JPA auditing in the Spring Data JPA
	
	@Basic(optional = true)

	// Important : About the types of Date & Time field
	// 	As soon as possible, the date & time fields should be LocalDate, LocalTime, LocalDateTime types.
	// 	Because the Date type is very difficult to operate Date in Java language after JAVA 8.
	private LocalDateTime createDate;


	// ---------------
	// 1st. method with the Hibernate JPA provider	- OK
	// ---------------
//	@UpdateTimestamp														// 1
//	@UpdateTimestamp(source = SourceType.VM)				// 2, Default

	// ---------------
	// 2nd. method with the Hibernate JPA provider - OK, *BEST
	// ---------------
//	@CurrentTimestamp														// 1
//	@CurrentTimestamp(source = SourceType.DB)				// 2, Default
//	@CurrentTimestamp(event =  {EventType.UPDATE})		// 3, Default, event =  {EventType.INSERT, EventType.UPDATE}
//	@CurrentTimestamp(timing)											// 4, `timing` element is @Deprecated(since="6.2")
	
	// 5, *BEST, Only When Updating(event), Set DB Timestamp(source)
	@CurrentTimestamp(event = EventType.UPDATE, source = SourceType.DB)

	@Temporal(TemporalType.TIMESTAMP)							// For seting Date & Time	 in the Standard JPA
	@LastModifiedDate															// For JPA auditing in the Spring Data JPA
	
	@Basic(optional = true)
	
	// Important : About the types of Date & Time field
	// 	As soon as possible, the date & time fields should be LocalDate, LocalTime, LocalDateTime types.
	// 	Because the Date type is very difficult to operate Date in Java language after JAVA 8.
	private LocalDateTime updateDate;
	
	
/**
	// Lifecycle callback of an Entity.
	// *Important: callback methods annotated on the bean class must (1) return void and (2) take no arguments.
	
	@PrePersist
	void prePersist() {
		log.trace("prePersist() invoked.");
	} // prePersist
	
	@PostPersist
	void postPersist() {
		log.trace("postPersist() invoked.");
	} // postPersist
	
	@PreUpdate
	void preUpdate() {
		log.trace("preUpdate() invoked.");
	} // preUpdate
	
	@PostUpdate
	void postUpdate() {
		log.trace("postUpdate() invoked.");
	} // postUpdate
	
	@PreRemove
	void preRemove() {
		log.trace("preRemove() invoked.");
	} // preRemove
	
	@PostRemove
	void postRemove() {
		log.trace("postRemove() invoked.");
	} // postRemove
	
	@PostLoad
	void postLoad() {
		log.trace("postLoad() invoked.");
	} // postLoad
*/

} // end class
