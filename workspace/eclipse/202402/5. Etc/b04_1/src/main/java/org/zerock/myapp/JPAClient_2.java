package org.zerock.myapp;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.zerock.myapp.domain.Board;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAClient_2 {

	
	public static void main(String[] args) throws Exception {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("b04_1");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {	// Entity CRUD, UPDATE (MODIFICATION)
			tx.begin();
			
			// -----------------------------------
			
			Board foundBoard = em.<Board>find(Board.class, 10l);
			log.info("\t+ foundBoard: {}", foundBoard);
			
			foundBoard.setTitle("- FOUND BOARD: 10l -");		// title modified.

			// ---------------------------------------------------------
			// No needed: automatically database table updated. (***)
			// ---------------------------------------------------------
			// If some fields updated, automatically all required update SQLs saved
			// in the SQL repository of the persistence context.
//			em.merge(foundBoard);
//			em.flush();
			

			// ---------------------------------------------------------
			// When TX finished,
			// ---------------------------------------------------------
			// All batch SQLs in the SQL repository of the persistence context executed at the same time (***)
			// ---------------------------------------------------------
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			
			throw e;
		} finally {
			em.close();
			emf.close();
		} // try-catch-finally
	} // main

} // end class
