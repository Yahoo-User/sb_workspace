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
public class JPAClient3 {

	private static Integer seq = 0;
	
	
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		++JPAClient3.seq;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("b04");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
//		------------------------------------------
		Board board = new Board();
		
		try {
			tx.begin();
			
			// -------------------------

//			board.setSeq(127l);
			
			board.setTitle("TITLE_"+JPAClient3.seq);
			board.setWriter("WRITER_"+JPAClient3.seq);
			board.setContent("CONTENT_"+JPAClient3.seq);
			board.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
			board.setCnt(0L);
			
			// -------------------------
			
			em.persist(board);
			em.flush();
			
			// -------------------------
			
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
			
			log.debug(board);
		} // try-catch-finally
	} // main

} // end class
