package org.zerock.myapp;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.zerock.myapp.domain.Board;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAUpdateClient {

	
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));

		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("AWS-RDS-MySQL");
		@Cleanup EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Board foundBoard = em.<Board>find(Board.class, 3L);

			Objects.requireNonNull(foundBoard);
			log.trace("\t+ foundBoard: {}", foundBoard);
			
			foundBoard.setTitle("MODIFIED_TITLE");
			foundBoard.setContent("MODIFIED CONTENT");
			
			// merge() method can be used when the entity was detached (***)
//			em.merge(foundBoard);
			
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			;;
		} // try-catch-finally
		
	} // main

} // end class
