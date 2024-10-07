package org.zerock.myapp.jpa;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.zerock.myapp.domain.BoardWithSequenceGenerator;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPABoardWithSequenceGenerator {

	
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));
		
		final String persistenceUnitName = "quickstart-ch04-1";

		// Step1. Create a javax.persistence.EntityManagerFactory object.
		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		
		// Step2. Create a javax.persistence.EntityManager object from EntityManagerFactory.
		@Cleanup EntityManager em = emf.createEntityManager();

		// Step3. Create a javax.persistence.EntityTransaction object from EntityManager.
		EntityTransaction tx = em.getTransaction();
		
		try {
			// Step4. Start a transaction automatically or manually.
			tx.begin();

			// Step5. Do CRUD for the entity object.
			
			// 5-1. CREATE
//			BoardWithSequenceGenerator board = new BoardWithSequenceGenerator();
//			
//			board.setTitle("Title");
//			board.setWriter("Writer");
//			board.setContent("Content");
//			board.setCnt(0L);
//			
//			em.persist(board);
			
			// 5-2. READ
			
			// 1st. method: found for 0 ~ 1
			BoardWithSequenceGenerator foundBoard = em.<BoardWithSequenceGenerator>find(BoardWithSequenceGenerator.class, 2L);
			log.trace("\t+ foundBoard: {}", foundBoard);
			
			// 2nd. method: found for 0 ~ N
			
			
			// 5-3. UPDATE
			foundBoard.setTitle("MODIFIED");
			foundBoard.setCnt(foundBoard.getCnt() + 1);

			em.merge(foundBoard);				// OK, 1st. method for Update (Default)
//			em.persist(foundBoard);				// OK, 2nd. method for Update
			
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			log.info("\t\t+ isActive: {}", tx.isActive());
		} // try-catch-finally
	} // main

} // end class

