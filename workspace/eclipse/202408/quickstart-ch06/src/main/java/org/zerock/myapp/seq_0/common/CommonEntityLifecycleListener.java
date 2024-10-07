package org.zerock.myapp.seq_0.common;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public final class CommonEntityLifecycleListener {
	
	
	@PrePersist 
	void prePersist(Object entity) {
		log.trace("(C) prePersist({}) invoked.", entity);
	} // prePersist
	
	@PostPersist
	void postPersist(Object entity) {
		log.trace("(C) postPersist({}) invoked.", entity);
	} // postPersist
	
	@PostLoad
	void postLoad(Object entity) {
		log.trace("(R) postLoad({}) invoked.", entity);
	} // postLoad
	
	@PreUpdate
	void preUpdate(Object entity) {
		log.trace("(U) preUpdate({}) invoked.", entity);
	} // preUpdate
	
	@PostUpdate
	void postUpdate(Object entity) {
		log.trace("(U) postUpdate({}) invoked.", entity);
	} // postUpdate
	
	@PreRemove
	void preRemove(Object entity) {
		log.trace("(D) preRemove({}) invoked.", entity);
	} // preRemove
	
	@PostRemove
	void postRemove(Object entity) {
		log.trace("(D) postRemove({}) invoked.", entity);
	} // postRemove

} // end class

