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
public class JPADeleteClient {

	
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));

		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("AWS-RDS-MySQL");
		@Cleanup EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			// To managed state in 1st. cache of the persistence context.
			Board foundBoard = em.<Board>find(Board.class, 3L);

			Objects.requireNonNull(foundBoard);
			log.trace("\t1. Found Board: {}", foundBoard);
			
			foundBoard.setTitle("MODIFIED_TITLE");
			foundBoard.setContent("MODIFIED CONTENT");
			log.trace("\t2. Modified Board: {}", foundBoard);
			
			// Delete Found Entity.
			em.remove(foundBoard);
			log.info("\t3. Removed Board: {}", foundBoard);
			
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			
			e.printStackTrace();
		} finally {
			;;
		} // try-catch-finally
		
	} // main

} // end class
