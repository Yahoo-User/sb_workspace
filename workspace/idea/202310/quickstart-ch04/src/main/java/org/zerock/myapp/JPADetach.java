package org.zerock.myapp;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.zerock.myapp.domain.Board;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;


@Log4j2
public class JPADetach {
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

		// Step3. Find an entity using specified entity type & primary key in the Database.
		Board foundBoard = entityManager.find(Board.class, 7L);		// -- Entity Lifecycle State : *MANAGED*
		log.info("\t+ foundBoard: {}", foundBoard);

		// Step4. Check whether the found *Managed* entity exists in the `Persistence Context`.
		boolean contained1 = entityManager.contains(foundBoard);
		log.info("\t+ 1. contained before detaching: {}", contained1);	// true.

		// Step5. From *MANAGED* to *DETACHED* by `detach(), clear(), close()` methods defined in the `EntityManager`.

		/* ---------------------------------------
		 * Step5-1. By `detach()` method.
		 * ---------------------------------------
		 * Remove the given entity from the persistence context, causing a *Managed* entity to become *Detached*.
		 * Un-flushed changes made to the entity if any (including removal of the entity),
		 * will not be synchronized to the database.
		 * Entities which previously referenced the *detached* entity will continue to reference it.
		 */
		entityManager.detach(foundBoard);						// -- Entity Lifecycle State : *DETACHED*

		/* ---------------------------------------
		 * Step5-2. By `clear()` method.
		 * ---------------------------------------
		 * Clear the persistence context, causing all *Managed* entities to become *Detached*.
		 * Changes made to entities that have *NOT* been flushed to the database will *NOT* be persisted.
		 */
//		entityManager.clear();									// -- Entity Lifecycle State : *DETACHED*

		/* ---------------------------------------
		 * Step5-3. By `close()` method.
		 * ---------------------------------------
		 * Close an application-managed entity manager.
		 *
		 * After the `close` method has been invoked, all methods on the `EntityManager` instance and
		 * any `Query`, `TypedQuery`, and `StoredProcedureQuery` objects obtained from it will throw
		 * the `IllegalStateException` except for `getProperties`, `getTransaction`, and `isOpen`
		 * (which will return false).
		 *
		 * If this method is called when the entity manager is joined to an *active* transaction,
		 * the persistence context remains managed *until* the transaction completes.
		 */
//		entityManager.close();								   // -- Entity Lifecycle State : *DETACHED*

		// Step6. Check whether the *detached* entity exists in the `Persistence Context`.
		// In Step5-3. by `close()` method. : java.lang.IllegalStateException: Session/EntityManager is closed
		boolean contained2 = entityManager.contains(foundBoard);
		log.info("\t+ 2. contained after detaching: {}", contained2);	// false.


		// Step7. Merge *Detached* Entity by `merge()` method defined in the `EntityManager`.
		//        *Detached* -> *Managed* by `merge()` method.

		entityManager.getTransaction().begin();			// Transaction Begins

//		foundBoard.setCnt(foundBoard.getCnt() + 1);		// OK: Before merge.

		/* ---------------------------------------
		 * EntityManager.merge(entity) method.
		 * ---------------------------------------
		 * Merge the state of the given entity into the current persistence context.
		 * Returns: the *Managed* instance that the state was merged to.
		 */
		Board mergedBoard = entityManager.merge(foundBoard);	// -- Entity Lifecycle State : *MANAGED*
		boolean contained3 = entityManager.contains(mergedBoard);
		log.info("\t+ 3. contained after merging: {}", contained3);		// true.

		mergedBoard.setCnt(mergedBoard.getCnt() + 1);	// OK : After merge.

		entityManager.getTransaction().commit();		// Transaction Committed.
	} // main

} // end class
