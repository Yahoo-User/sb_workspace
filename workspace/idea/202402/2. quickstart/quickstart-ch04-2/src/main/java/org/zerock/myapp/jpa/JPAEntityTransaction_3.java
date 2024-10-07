package org.zerock.myapp.jpa;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAEntityTransaction_3 {

	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));
		
		final String persistenceUnitName = "quickstart-ch04-2";
		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		@Cleanup EntityManager em = emf.createEntityManager();
		
		/**
		 * -----------------------------------------
		 * Interface EntityTransaction
		 * -----------------------------------------
		 * Interface used to control transactions on resource-local entity managers.
		 * 
		 * The EntityManager.getTransaction() method returns the EntityTransaction interface.
		 * 
		 * 		Since:	Java Persistence 1.0
		 * -----------------------------------------
		 */
		EntityTransaction tx = em.getTransaction();
		
		log.info("\t+ tx: {}", tx);
		log.info("\t+ isActive1: {}", tx.isActive());

		tx.begin();										// Start a resource transaction. 
		
		/**
		 * -----------------------------------------
		 * EntityTransaction.setRollbackOnly() method
		 * -----------------------------------------
		 * Mark the current resource transaction 
		 * so that the only possible outcome of the transaction is for the transaction to be rolled back. 
		 * 
		 * Throws:	IllegalStateException - if isActive() is false
		 * -----------------------------------------
		 */
		tx.setRollbackOnly();

		log.info("\t+ isActive2: {}", tx.isActive());
		log.info("\t+ getRollbackOnly: {}", tx.getRollbackOnly());
		
		/**
		 * -----------------------------------------
		 * EntityTransaction.commit() method
		 * -----------------------------------------
		 * Commit the current resource transaction, writing any `unflushed` changes to the database. 
		 * 
		 * 		Throws:	IllegalStateException - if isActive() is false
		 * 		RollbackException - if the commit fails
		 * -----------------------------------------
		 */
//		tx.commit();
		
		/**
		 * -----------------------------------------
		 * EntityTransaction.rollback() method
		 * -----------------------------------------
		 * Roll back the current resource transaction. 
		 * 
		 * 		Throws:	IllegalStateException - if isActive() is false
		 * 		PersistenceException - if an unexpected errorcondition is encountered
		 * -----------------------------------------
		 */
		tx.rollback();
		
		log.info("\t+ isActive3: {}", tx.isActive());
		
		em.clear();
	} // main

} // end class

