package org.zerock.myapp.jpa;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.zerock.myapp.domain.Board6;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPABoard {

	// Step1. Create a javax.persistence.EntityManagerFactory object with javax.persistence.Persistence static method.
	//			EntityManagerFactory object is a Resource object. After using, close it definitely.
	// Step2. Create a javax.persistence.EntityManager object from EntityManagerFactory.
	// Step3. Create a javax.persistence.EntityTransaction object from EntityManager.
	// Step4. Start a transaction automatically or manually.
	// Step5. Update data using Entity object.
	// Step6. Complete a transaction with EntityTransaction.commit() or rollback() methods to persist data changes.
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));
		
		final String persistenceUnitName = "quickstart-ch04-1";
		
		// Step1. Create a javax.persistence.EntityManagerFactory object.
		
		/*
				1. drop table if exists board CASCADE
				2. drop sequence if exists hibernate_sequence
				3. create sequence hibernate_sequence start with 1 increment by 1
				4. create table board (					// If only hibernate.hbm2ddl.auto sets to `create`, NOT `update`.
						seq bigint not null,
						cnt bigint,
						content varchar(255),
						createDate timestamp,
						title varchar(255),
						writer varchar(255),
						primary key (seq)
					)
		*/
		@Cleanup
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		log.info("\tStep1. EntityManagerFactory: {}", emf);
		
		// Step2. Create a javax.persistence.EntityManager object from EntityManagerFactory.
		@Cleanup
		EntityManager em = emf.createEntityManager();
		log.info("\tStep2. EntityManager: {}", em);
		
		// Step3. Create a javax.persistence.EntityTransaction object from EntityManager.
		EntityTransaction tx = em.getTransaction();
		log.info("\tStep3. EntityTransaction: {}", tx);
		
		try {
			// Step4. Start a transaction automatically or manually.
			tx.begin();							// IllegalStateException - if isActive() is true
			
			log.info("\tStep4. Started a transaction.");

			// Step5. Update data using Entity object.
			log.info("\tStep5. Update data using Entity object.");
			
			// ---------------
			// 1. CREATE(= INSERT)
			// ---------------
			
//			Board board = new Board();
//			Board2 board = new Board2();
//			Board5 board = new Board5();
			Board6 board = new Board6();
			
			// 1-1. The value of seq with `@Id` field automatically created, when used with @GeneratedValue.
			
			// 1-2. Set id value manually If entity class might NOT have `@GeneratedValue` annotation with `@Id` field.
			// 		 Thus if setting seq (pk) directly to persist, the following exception occurred:
			//				org.hibernate.PersistentObjectException: `detached entity` passed to persist
//			 board.setSeq(333L);
			
			board.setTitle("TITLE");
			board.setWriter("WRITER");
			board.setContent("CONTENT");
			board.setCreateDate(LocalDateTime.now());
			board.setCnt(0L);
			
			log.info("\t\t+ Before persisting: {}", board);
			em.persist(board);			// Create
			log.info("\t\t+ After persisting: {}", board);
			
			// ---------------
			// 2. READ(= SELECT)
			// ---------------
			// Note: To find some entity, use primary key of an entity to be found.
			
//			Board foundBoard = em.<Board>find(Board.class, 1L);
//			Board5 foundBoard = em.<Board5>find(Board5.class, 1L);
//			Board6 foundBoard = em.<Board6>find(Board6.class, 1L);
//			
//			log.info("\t\t+ foundBoard: {}", foundBoard);
			
			// ---------------
			// 3. UPDATE
			// ---------------
			// Note: To update a existed entity, you first must find an entity to update.
			
//			foundBoard.setTitle("MODIFIED");
//			foundBoard.setCnt(foundBoard.getCnt() + 1);
//			foundBoard.setUpdateDate(new Date());
//			
//			em.merge(foundBoard);			// OK, 1st. method for Update (Default)
//			em.persist(foundBoard);			// OK, 2nd. method for Update
			
			// ---------------
			// 4. DELETE
			// ---------------
			// 4-1. Note: To delete a existed entity, you must find an entity to delete firstly.
//			em.remove(foundBoard);
			
			// 4-2. java.lang.IllegalArgumentException: Removing a detached instance org.zerock.myapp.domain.Board#2

			/*
			 * ----------------------------------------------
			 * EntityManager.detach(entity object) method
			 * ----------------------------------------------
			 * Remove the given entity from the `persistence context`, causing a managed entity to become `detached`.
			 * Unflushed changes made to the entity if any (including removal of the entity), will NOT be synchronized to the database.
			 * Entities which previously referenced the `detached entity` will continue to reference it.
			 *
			 * Parameters: entity entity instance
			 * Throws: IllegalArgumentException - if the instance is NOT an entity
			 *
			 * Since:• Java Persistence 2.0
			 */
//			em.detach(foundBoard);
//			em.remove(foundBoard);
			
			// Step6. Complete a transaction with EntityTransaction.commit() or rollback() methods.
			tx.commit();	 						// IllegalStateException - if isActive() is false
														// RollbackException 		 - if the commit fails
		} catch(Exception e) {
			e.printStackTrace();

			// Step6. Complete a transaction with EntityTransaction.commit() or rollback() methods.
			tx.rollback();						// IllegalStateException - if isActive() is false
														// PersistenceException - if an unexpected error condition is encountered
		} finally {
			log.info("\tStep6. Complete a transaction with EntityTransaction.commit() or rollback() methods");
			log.info("\t\t+ isActive: {}", tx.isActive());
		} // try-catch-finally
	} // main

} // end class


