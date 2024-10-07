package org.zerock.myapp;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.zerock.myapp.domain.Board;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPASelectAll {
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

		// Step3. Define a `JPQL` query.
//		String jpql = "SELECT b FROM Board b ORDER BY b.seq DESC";		// OK
		String jpql = "from Board";										// OK

		// Step4. Create a `Typed` Query.
		TypedQuery<Board> typedQuery = entityManager.createQuery(jpql, Board.class);
		log.info("\t+ typedQuery: {}", typedQuery);

		// Step5. Execute the JPQL and Get Result Set.
		 List<Board> list = typedQuery.getResultList();		// -- Entity Lifecycle State : *MANAGED*
		list.forEach(log::info);

		entityManager.getTransaction().begin();				// -- Transaction Begins.

		list.forEach(board -> {
			// Step6. Check whether the found *Managed* entity exists in the `Persistence Context`.
			boolean contained = entityManager.contains(board);
			log.info("\t+ contained: {}", contained);

			// Step7. Update *Managed* Entity. -> OK
			board.setCnt(board.getCnt() + 1);
		});	// .forEach

		// When Transaction committed, Auto-flushed. (***)
//		entityManager.flush();	// OK : Synchronize the persistence context to the underlying database.

		entityManager.getTransaction().commit();			// -- Transaction Committed.
	} // main

} // end class
