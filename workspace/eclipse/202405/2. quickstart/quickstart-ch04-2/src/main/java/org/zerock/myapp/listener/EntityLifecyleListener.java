package org.zerock.myapp.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class EntityLifecyleListener {

	
	@PrePersist
	void prePersist(Object entity) {
		log.trace("prePersist({}) invoked.", entity);
	} // prePersist
	
	@PostPersist
	void postPersist(Object entity) {
		log.trace("postPersist({}) invoked.", entity);
	} //postPersist
	
	@PreUpdate
	void preUpdate(Object entity) {
		log.trace("preUpdate({}) invoked.", entity);
	} // preUpdate
	
	@PostUpdate
	void postUpdate(Object entity) {
		log.trace("postUpdate({}) invoked.", entity);
	} // postUpdate
	
	@PreRemove
	void preRemove(Object entity) {
		log.trace("preRemove({}) invoked.", entity);
	} // preRemove
	
	@PostRemove
	void postRemove(Object entity) {
		log.trace("postRemove({}) invoked.", entity);
	} // postRemove
	
	@PostLoad
	void postLoad(Object entity) {
		log.trace("postLoad({}) invoked.", entity);
	} // postLoad

} // end class
