package org.zerock.myapp;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.zerock.myapp.domain.Board;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPASelect {
	// Defined in the `classpath:/META-INF/persistence.xml` JPA configuration file.
	private static final String persistenceUnitName = "H2";

	
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));

		// Step1. Create a `EntityManagerFactory` which is *NOT* AutoCloseable, But have `close()` method.
		@Cleanup
		EntityManagerFactory entityManagerFactory =
				Persistence.createEntityManagerFactory(persistenceUnitName);

		// Step2. Create a `EntityManager` from `EntityManagerFactory` to manage `Persistence Context` . 	(***)
		//		  This time, New *Persistence Context* (like *Entity Container*) is created. 				(***)
		@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();

		// Step3. Find an entity using specified entity type & primary key in the Database.
		Board foundBoard = entityManager.find(Board.class, 1L);		// -- Entity Lifecycle State : *MANAGED*
		log.info("\t+ foundBoard: {}", foundBoard);

		// Step4. Check whether the found *Managed* entity exists in the `Persistence Context`.
		boolean contained = entityManager.contains(foundBoard);
		log.info("\t+ contained: {}", contained);

		// Step5. Update *Managed* Entity. -> OK
		foundBoard.setCnt(foundBoard.getCnt() + 1);

		// Step6. Flush Updated *Managed* Entity.
		// XX : Transaction Required to update a Entity. (***)
		// 		javax.persistence.TransactionRequiredException: no transaction is in progress.
		// Cause: if there is no transaction or if the entity manager has not been joined to the current transaction.
//		entityManager.flush();	// Synchronize the persistence context to the underlying database.
	} // main

} // end class
