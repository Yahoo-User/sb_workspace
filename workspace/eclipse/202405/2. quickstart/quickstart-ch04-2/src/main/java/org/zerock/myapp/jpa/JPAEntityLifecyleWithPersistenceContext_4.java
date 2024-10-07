package org.zerock.myapp.jpa;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.zerock.myapp.domain.Person;
import org.zerock.myapp.util.Persistences;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAEntityLifecyleWithPersistenceContext_4 {

	
	/**
	 * -----------------------------------------
	 * 1. EntityManager.persist(transientEntity) method
	 * -----------------------------------------
	 * Make an instance `managed` and `persistent`.
	 * 
	 * 		Parameters:	 entity 		entity instance
	 * 		Throws:	EntityExistsException 				- if the entity ALREADY exists. 
	 * 						( If the entity ALREADY exists, the `EntityExistsException` may be thrown
	 * 						  when the persist operation is invoked, or the `EntityExistsException` or another `PersistenceException` may be thrown at `flush` or `commit` time. ) 
	 * 						IllegalArgumentException 			- if the instance is NOT an entity
	 * 						TransactionRequiredException 	- if there is NO transaction when invoked on a container-managed entity manager of that is of type `PersistenceContextType.TRANSACTION`.
	 * 
	 * -----------------------------------------
	 * 2. EntityManager.flush() method
	 * -----------------------------------------
	 * Synchronize the persistence context to the underlying database.
	 * 
	 * 		Throws:	TransactionRequiredException - if there is NO transaction or if the entity manager has NOT been joined to the current transaction
	 * 						PersistenceException - if the flush fails
	 * 
	 * -----------------------------------------
	 * 3. <T>  T EntityManager.find(Class<T> entityClass, Object primaryKey) method
	 * -----------------------------------------
	 * Find by primary key.
	 * 
	 * Search for an entity of the specified class and primary key.
	 * If the entity instance is contained in the persistence context, it is returned from there.
	 * 
	 * 		Type Parameters:		<T> 
	 * 
	 * 		Parameters:
	 * 				entityClass entity class
	 * 				primaryKey primary key
	 * 
	 * 		Returns:	the found entity instance or `null` if the entity does NOT exist
	 * 
	 * 		Throws:	IllegalArgumentException - if the first argument does NOT denote an entity type 
	 * 																	  or the second argument is NOT a valid type for that entity's primary key or is null
	 * 
	 * -----------------------------------------
	 * 4. EntityManager.detach(managedEntity)
	 * -----------------------------------------
	 * Remove the given entity from the persistence context, causing a managed entity to become detached.
	 * 
	 * Unflushed changes made to the entity if any (including removal of the entity), will NOT be synchronized to the database.
	 * Entities which previously referenced the detached entity will continue to reference it.
	 * 
	 * 		Parameters:	entity entity instance
	 * 		Throws:	IllegalArgumentException - if the instance is NOT an entity 
	 * 		Since:	Java Persistence 2.0
	 * 
	 * -----------------------------------------
	 * 5. EntityManager.merge(detachedEntity)
	 * -----------------------------------------
	 * Merge the state of the given entity into the current persistence context.
	 * 
	 * 		Type Parameters:	<T> 
	 * 		Parameters: entity entity instance
	 * 		Returns:	 the managed instance that the state was merged to
	 * 		Throws:	IllegalArgumentException - if instance is NOT an entity or is a removed entity
	 * 		TransactionRequiredException - if there is NO transaction when invoked on a container-managed entity manager of that is of type `PersistenceContextType.TRANSACTION`
	 * 
	 * -----------------------------------------
	 * 6. EntityManager.remove(managedEntity) method
	 * -----------------------------------------
	 * Remove the entity instance.
	 * 
	 * 		Parameters:	entity entity instance
	 * 		Throws:	IllegalArgumentException - if the instance is NOT an entity or is a `detached` entity
	 * 						TransactionRequiredException - if invoked on a container-managed entity manager of type `PersistenceContextType.TRANSACTION` 
	 * 																			and there is NO transaction
	 * 
	 * -----------------------------------------
	 * 7. EntityManager.clear() method
	 * -----------------------------------------
	 * Clear the persistence context, causing all `managed` entities to become `detached`.
	 * Changes made to entities that have NOT been flushed to the database will NOT be persisted.
	 * 
	 * -----------------------------------------
	 * 8. EntityManager.close() method
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
	 * Throws: IllegalStateException - if the entity manager is container-managed
	 */
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));

		//-- 1 ------------
		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistences.persistenceUnitName);
		@Cleanup EntityManager em = emf.createEntityManager();


		//-- 2 ------------
		// Step1. Create an Transient Entity Object, But Not Yet Persisted.
		
		log.info("-- 2 ------------");
		
		Person transientPerson = new Person();							// Transient
		transientPerson.setName("NAME");
		transientPerson.setAge(23);

		boolean isContains = em.contains(transientPerson);													// false
		boolean isLoaded = Persistence.getPersistenceUtil().isLoaded(transientPerson);		// true
		
		log.info("Step1. transientPerson( {} ), isContains( {} ), isLoaded( {} )", transientPerson, isContains, isLoaded);


		//-- 3 ------------
		// Step2. Persist an Transient Entity Object.
		
		log.info("-- 3 ------------");
		
		try {
			em.getTransaction().begin();
		
			em.persist(transientPerson);			// Transient -> Managed
			em.flush();										// Synchronize the persistence context to the underlying database.
		
			em.getTransaction().commit();		// Commit the current resource transaction, writing any unflushed changes to the database. 
		} catch(Exception e) {
			em.getTransaction().rollback();		// Roll back the current resource transaction. 
			e.printStackTrace();
		} // try-catch
		
		Person persistedPerson = transientPerson;
		
		isContains = em.contains(persistedPerson);														// true
		isLoaded = Persistence.getPersistenceUtil().isLoaded(persistedPerson);		// true
		
		log.info("Step2. persistedPerson( {} ), isContains( {} ), isLoaded( {} )", persistedPerson, isContains, isLoaded);


		//-- 4 ------------
		// Step3. Find an Entity By Primary Key From the Underlying Database:  (1) Persisted  (2) Detached  (3) Merged
		
		log.info("-- 4 ------------");
		
		// Clear the persistence context, causing all managed entities to become detached.
		// Changes made to entities that have Not been flushed to the database will Not be persisted.
		em.clear();
		
		Long pk = persistedPerson.getId();
		
		Person foundPerson = em.<Person>find(Person.class, pk);		// The state in the lifecycle of an entity : Database -> Managed
		Objects.requireNonNull(foundPerson);
		
		isContains = em.contains(foundPerson);													// true
		isLoaded = Persistence.getPersistenceUtil().isLoaded(foundPerson);		// true
		
		log.info("Step3. foundPerson( {} ), isContains( {} ), isLoaded( {} )", foundPerson, isContains, isLoaded);


		//-- 5 ------------
		// Step4. Detach the Found Entity from the Underlying Database.
		
		log.info("-- 5 ------------");
		
		try {
			em.getTransaction().begin();
		
			em.detach(foundPerson);					// OK - The state in the lifecycle of an entity : Managed -> Detached
//			em.clear();											// OK - The state in the lifecycle of an entity : Managed -> Detached
//			em.close();											// XX - IllegalStateException: Session/EntityManager is closed
		
//			em.flush();
		
			em.getTransaction().commit();
		} catch(Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} // try-catch

		Person detachedPerson = foundPerson;
		
		isContains = em.contains(detachedPerson);														// false
		isLoaded = Persistence.getPersistenceUtil().isLoaded(detachedPerson);		// true
		
		log.info("Step4. detachedPerson( {} ), isContains( {} ), isLoaded( {} )", detachedPerson, isContains, isLoaded);


		//-- 6 ------------
		// Step5. Merge an Detached Entity.
		
		log.info("-- 6 ------------");
		
		try {
			em.getTransaction().begin();			// Transaction required when merging an detached entity.		(***)
		
			detachedPerson.setName("MODIFIED");
			detachedPerson.setAge(33);
			
			em.merge(detachedPerson);			// XX : The state in the lifecycle of an entity : Detached -> Managed
//			em.flush();
		
			em.getTransaction().commit();
		} catch(Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} // try-catch
		
		Person mergedPerson = detachedPerson;
		
		isContains = em.contains(mergedPerson);													// false
		isLoaded = Persistence.getPersistenceUtil().isLoaded(mergedPerson);		// true
		
		log.info("Step5. mergedPerson( {} ), isContains( {} ), isLoaded( {} )", mergedPerson, isContains, isLoaded);


		//-- 7 ------------
		// Step6. Remove the Merged Entity Founded from the Underlying Database.
		
		log.info("-- 7 ------------");

		try {
			em.getTransaction().begin();
			
			// XX - IllegalArgumentException: Removing a `detached` instance org.zerock.myapp.domain.Person#41
			// em.remove(mergedPerson);			// XX : The state in the lifecycle of an entity : Merged -> Removed
			
			// XX - PersistentObjectException: detached entity passed to persist: org.zerock.myapp.domain.Person
			// em.persist(mergedPerson);			// XX : The state in the lifecycle of an entity : Merged -> Managed
			
			// 6-1. The state in the lifecycle of an entity : Database -> Managed
			//			(1) Persisted
			//			(2) Detached
			//			(3) Merged
			foundPerson = em.find(Person.class, mergedPerson.getId());
			
			
			//  6-2. The state in the lifecycle of an entity : Managed -> Removed
			em.remove(foundPerson);
//			em.flush();										// No required. Synchronize the persistence context to the underlying database.
			
			em.getTransaction().commit();		// Commit the current resource transaction, writing any unflushed changes to the database. 
		} catch(Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} // try-catch
		
		Person removedPerson = foundPerson;
		
		isContains = em.contains(removedPerson);													// false
		isLoaded = Persistence.getPersistenceUtil().isLoaded(mergedPerson);		// true
		
		log.info("Step6. removedPerson( {} ), isContains( {} ), isLoaded( {} )", removedPerson, isContains, isLoaded);


		//-- 8 ------------
		// Step7. Clear all Entities from the Persistence Context.
		
		em.clear();
	} // main

} // end class

