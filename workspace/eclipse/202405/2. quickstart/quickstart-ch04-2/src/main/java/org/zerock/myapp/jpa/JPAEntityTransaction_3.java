package org.zerock.myapp.jpa;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.zerock.myapp.domain.Person;
import org.zerock.myapp.util.Persistences;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAEntityTransaction_3 {

	
	/**
	 * -----------------------------------------
	 * 1. Interface EntityTransaction
	 * -----------------------------------------
	 * Interface used to control transactions on resource-local entity managers.
	 * The EntityManager.getTransaction() method returns the EntityTransaction interface.
	 * 
	 * 		Since:	Java Persistence 1.0
	 * 
	 * -----------------------------------------
	 * 2. EntityTransaction.setRollbackOnly() method
	 * -----------------------------------------
	 * Mark the current resource transaction 
	 * so that the only possible outcome of the transaction is for the transaction to be rolled back. 
	 * 
	 * 		Throws:	IllegalStateException - if isActive() is false
	 * 
	 * -----------------------------------------
	 * 3. EntityTransaction.commit() method
	 * -----------------------------------------
	 * Commit the current resource transaction, writing any `unflushed` changes to the database. 
	 * 
	 * 		Throws:	IllegalStateException - if isActive() is false
	 * 		RollbackException - if the commit fails
	 * 
	 * -----------------------------------------
	 * 4. EntityTransaction.rollback() method
	 * -----------------------------------------
	 * Roll back the current resource transaction. 
	 * 
	 * 		Throws:	IllegalStateException - if isActive() is false
	 * 		PersistenceException - if an unexpected errorcondition is encountered
	 */
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));

		//-- 1 ------------
		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistences.persistenceUnitName);
		@Cleanup EntityManager em = emf.createEntityManager();

		
		//-- 2 ------------
		EntityTransaction tx = em.getTransaction();
		
		log.info("\t+ tx: {}", tx);

		
		//-- 3 ------------
		Person transientPerson = new Person();
		transientPerson.setName("Yoseph");
		transientPerson.setAge(23);
		
		try {
			log.info("\t+ isActive1: {}", tx.isActive());
			
			tx.begin();										// Start transaction.
			
			log.info("\t+ isActive2: {}", tx.isActive());
			
//			tx.setRollbackOnly();
//			log.info("\t+ getRollbackOnly: {}", tx.getRollbackOnly());
			
			em.persist(transientPerson);			// INSERT

			tx.commit();									// End transaction
			
			log.info("\t+ Done.");
		} catch(Exception e) {
			tx.rollback();									// End transaction
			e.printStackTrace();
		} finally {
			log.info("\t+ isActive3: {}", tx.isActive());
		} // try-catch-finally

		
		//-- 4 ------------
		em.clear();
	} // main

} // end class

