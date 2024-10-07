package org.zerock.myapp.jpa;

import java.util.Arrays;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.zerock.myapp.util.Persistences;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAEntityManager_2 {

	
	/**
	 * -----------------------------------------
	 * 1. Interface EntityManager
	 * -----------------------------------------
	 * Interface used to interact with the persistence context.
	 * 
	 * An EntityManager instance is associated with a persistence context.
	 * 
	 * A persistence context is a set of entity instances in which for any persistent entity identity there is a unique entity instance.
	 * Within the persistence context, the entity instances and their lifecycle are managed.
	 * 
	 * The EntityManager API is used to create and remove persistent entity instances, to find entities by their primary key, and to query over entities. 
	 * 
	 * The set of entities that can be managed by a given EntityManager instance is defined by a persistence unit.
	 * A persistence unit defines the set of all classes that are related or grouped by the application, and which must be colocated in their mapping to a single database.
	 * 
	 * 		Since:		Java Persistence 1.0
	 * 
	 * 		See Also:	Query
	 * 						TypedQuery
	 * 						CriteriaQuery
	 * 						PersistenceContext
	 * 						StoredProcedureQuery
	 * 
	 * -----------------------------------------
	 * 2. EntityManager.getProperties() method
	 * -----------------------------------------
	 * Get the properties and hints and associated values that are in effect for the entity manager.
	 * Changing the contents of the map does NOT change the configuration in effect.
	 * 
	 * 		Returns:	map of properties and hints in effect for entity manager
	 * 
	 * 		Since:• Java Persistence 2.0
	 * 
	 * -----------------------------------------
	 * 3. EntityManager.clear() method
	 * -----------------------------------------
	 * Clear the persistence context, causing all `managed` entities to become `detached`.
	 * Changes made to entities that have NOT been `flushed` to the database will NOT be persisted.
	 * 
	 * -----------------------------------------
	 * 4. EntityManager.close() method
	 * -----------------------------------------
	 * Close an application-managed `entity manager`.
	 * 
	 * After the `close` method has been invoked,
	 * 		(1) all methods on the `EntityManager` instance and 
	 * 		(2) any Query, TypedQuery, and  StoredProcedureQuery objects obtained from it 
	 * will throw the IllegalStateException except for `getProperties`, `getTransaction`, and `isOpen` (which will return false).
	 * 
	 * If this method is called when the entity manager is joined to an active transaction,
	 * the persistence context remains managed until the transaction completes. 
	 * 
	 * 		Throws: IllegalStateException - if the entity manager is container-managed
	 */
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));

		//-- 1 ------------
		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistences.persistenceUnitName);

		
		//-- 2 ------------
		@Cleanup EntityManager em = emf.createEntityManager();
		log.info("\t+ em: {}", em);		// SessionImpl(2008821270<open>)

		
		//-- 3 ------------
		Map<String, Object > properties = em.getProperties();
		
		properties.forEach((k, v) -> {
			log.info("\t+ Key( {} ), Val( {} )", k, v);
		});		// .forEach
		
		
		//-- 4 ------------
		em.clear();
	} // main

} // end class

