package org.zerock.myapp;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.zerock.myapp.domain.Board;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAClient2 {

	
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));

		EntityManagerFactory emf=Persistence.createEntityManagerFactory("b04");
		log.info("\t+ 1. emf : {}", emf);
		
		EntityManager em = emf.createEntityManager();
		log.info("\t+ 2. em  : {}", em);
		
		EntityTransaction tx = em.getTransaction();
		log.info("\t+ 3. tx  : {}, isActive: {}", tx, tx.isActive());
		
		// -----------------------
		
//		tx.begin();
			
			// If No entities found, return `null`.
			Board board = em.<Board>find(Board.class, 3L);
			log.info("4. found board: {}", board);

//			em.flush();	// This flush method *REQUIRES* a in-progress transaction (***)
		
//		tx.commit();
		
		// -----------------------
		em.close();
		emf.close();
	} // main

} // end class
