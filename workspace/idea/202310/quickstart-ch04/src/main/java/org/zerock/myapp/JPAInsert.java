package org.zerock.myapp;

import java.util.Arrays;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.zerock.myapp.domain.Board;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAInsert {
	// Defined in the `classpath:/META-INF/persistence.xml` JPA configuration file.
	private static final String persistenceUnitName = "H2";
	
	
    public static void main( String[] args ) {
    	log.trace("main({}) invoked.", Arrays.toString(args));

		// Step1. Create a `EntityManagerFactory` which is *NOT* AutoCloseable, But have `close()` method.
		@Cleanup EntityManagerFactory entityManagerFactory =
						Persistence.createEntityManagerFactory(persistenceUnitName);

		// Step2. Create a `EntityManager` from `EntityManagerFactory` to manage `Persistence Context` . 	(***)
		//		  This time, New *Persistence Context* (like *Entity Container*) is created. 				(***)
    	@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	// Step3. Create a `EntityTransaction` from `EntityManager`.
    	EntityTransaction tx = entityManager.getTransaction();
    	
    	try {
    		// Step4. Transaction Begins
    		tx.begin();

    		Board board = new Board();			// -- Entity Lifecycle State : *NEW*
    		
    		board.setTitle(UUID.randomUUID().toString());
    		board.setContent("NEW_CONTENT");
    		board.setWriter("Yoseph");
    		
    		log.info("\t+ Before : {}", board);
    		
    		// Step5. To save a *New* entity into `Persistence Context` by `EntityManager`
			entityManager.persist(board);		// -- Entity Lifecycle State : *MANAGED*
			entityManager.flush();				// -- Flushed a *MANAGED* Entity to the Database Permanently.

    		log.info("\t+ After : {}", board);
    		
    		// Step6. Commit a Transaction.
    		tx.commit();

			// Step7. Check whether the specified *Managed* entity exists in the `Persistence Context`.
			boolean contained = entityManager.contains(board);
			log.info("\t+ contained: {}", contained);
    	} catch(Exception e) {
    		// Step7. Rollback a Transaction.
    		tx.rollback();
    		
    		throw e;
    	} // try-finally
    } // main
    
} // end class
