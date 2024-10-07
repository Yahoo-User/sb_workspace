package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.zerock.myapp.domain.Board;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;


@Log4j2
public class JPAClient {

	
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_1");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Board board = new Board();
			board.setTitle("TITLE");
			board.setContent("CONTENT");
			board.setWriter("WRITER");
			
			log.info("\t+ board: {}", board);
			
			em.persist(board);
			
			tx.commit();
		} catch(Exception e) { 
			tx.rollback();
			
			throw e;
		} finally {
			em.close();
			emf.close();
		} // try-finally
	} // main

} // end class
