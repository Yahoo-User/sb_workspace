package org.zerock.myapp.seq_4.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.zerock.myapp.common.CommonEntityLifecycleListener;

import jakarta.persistence.Basic;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Data

@EntityListeners(CommonEntityLifecycleListener.class)
@MappedSuperclass
public abstract class JpaAudit implements Serializable {
	@Serial private static final long serialVersionUID = 1L;

	// -----------------------------
	// JPA Auditing Fields
	// -----------------------------
	
	@CurrentTimestamp(event = EventType.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Basic(optional = false)
	@CreatedDate protected Date createDate;
	
	@CurrentTimestamp(event = EventType.UPDATE)
	@Temporal(TemporalType.TIMESTAMP)
	@Basic(optional = true)
	@LastModifiedDate protected Date updateDate;
	
	
} // end class

