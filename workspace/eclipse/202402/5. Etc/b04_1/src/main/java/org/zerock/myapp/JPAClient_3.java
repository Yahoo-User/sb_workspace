package org.zerock.myapp;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.zerock.myapp.domain.Board;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAClient_3 {

	
	public static void main(String[] args) throws Exception {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("b04_1");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {	// Entity CRUD, DELETE (REMOVE)
			tx.begin();
			
			// -----------------------------------
			
			// 1. find entities to delete and save found entities into the persistence context.
			Board foundBoard = em.find(Board.class, 4l);
			log.info("\t+ found entity : {}", foundBoard);
			
			// 2. delete found entity in the persistence state.
			assert foundBoard != null;
//			Objects.requireNonNull(foundBoard);
			
			em.remove(foundBoard);
			
			// -----------------------------------
			
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
