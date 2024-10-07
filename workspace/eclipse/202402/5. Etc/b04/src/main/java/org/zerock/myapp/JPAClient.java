package org.zerock.myapp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.zerock.myapp.domain.Board;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAClient {
	
	private static int seq = 0;

	
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		++JPAClient.seq;
		
		// 1. Create Hibernate built-in connection pool.
		// 2. DDL execution for the specified `Entity`. i.e: CREATE TABLE, CREATE SEQUENCE, ...
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("b04");
		log.info("\t+ emf: {}", emf);
		
		// 1. Create a session using the previous Hibernate connection pool for H2 database.
		// 2. Required to create a new transaction.
		EntityManager em = emf.createEntityManager();
		log.info("\t+ em : {}", em);
		
		// 1. Create a transaction. but not begun (isActive: false)
		EntityTransaction tx = em.getTransaction();
		log.info("\t+ tx: {}, isActive: {}", tx, tx.isActive());
		
		try {
			// 2. Set this transaction active.
			tx.begin();
			log.info("\t+ tx isActive: {}", tx.isActive());
			
			Board board = new Board();
			
			board.setTitle("TITLE_"+JPAClient.seq);
			board.setWriter("WRITER_"+JPAClient.seq);
			board.setContent("CONTENT_"+JPAClient.seq);
			board.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
			board.setCnt(0L);
			
			// 6. Executing CRUD for the Entity.
			em.persist(board);
			
			// 7. Synchronize the persistence context to the underlying database.
			//    (*NOTE*) This flush requires in-progress transaction (***)
			//    If this flush method not invoked, NO transaction required. (***)
			em.flush();
			
			// 3. Commit for the TX.
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			
			// 3. Rollback for the TX.
			tx.rollback();
		} finally {
			// 4. Resource Release.
			em.close();
			emf.close();
		} // try-finally
	} // main

} // end class
