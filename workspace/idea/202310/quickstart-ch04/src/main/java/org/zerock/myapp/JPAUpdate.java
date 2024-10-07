package org.zerock.myapp;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.zerock.myapp.domain.Board;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;


@Log4j2
public class JPAUpdate {
	// Defined in the `classpath:/META-INF/persistence.xml` JPA configuration file.
	private static final String persistenceUnitName = "H2";

	
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));

		// Step1. Create a `EntityManagerFactory` which is *NOT* AutoCloseable, But have `close()` method.
		@Cleanup EntityManagerFactory entityManagerFactory =
						Persistence.createEntityManagerFactory(persistenceUnitName);

		// Step2. Create a `EntityManager` from `EntityManagerFactory` to manage `Persistence Context` . 	(***)
		//		  This time, New *Persistence Context* (like *Entity Container*) is created. 				(***)
		@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();

		// Step3. Create a `EntityTransaction` from `EntityManager`
		EntityTransaction tx = entityManager.getTransaction();
		
		try {
			// Step4. Transaction Begins
			tx.begin();

			// Step5. Find an entity using specified entity type & primary key in the Database.
			Board foundBoard = entityManager.find(Board.class, 4L);		// -- Entity Lifecycle State : *MANAGED*
			log.trace("\t+ foundBoard: {}", foundBoard);

			// Step6-1. Update *Managed* entity. -> OK
			//	- Automatically found entity in the *active* transaction belongs to the persistence context. (***)
			//	- Updating *Managed* Entity flushed automatically when TX completes. (***)
			foundBoard.setCnt(foundBoard.getCnt() + 1);

			// Step6-2. Update the *Detached* entity to be *Managed* in the `Persistence Context`. 	(***)
			// 		  `merge()` method can be used when the entity is *Detached*. 					(***)
//			entityManager.merge(foundBoard);	// OK

			// Step7. When Transaction committed, Auto-flushed. (***)
			// 		  Synchronize the persistence context to the underlying database.
//			entityManager.flush();				// -- Flushed a *MANAGED* Entity to the Database Permanently.

			// Step8. Transaction Committed.
			tx.commit();
		} catch(Exception e) {
			// Step8. Transaction Rolled Back.
			tx.rollback();

			throw e;
		} // try-catch
	} // main

} // end class
