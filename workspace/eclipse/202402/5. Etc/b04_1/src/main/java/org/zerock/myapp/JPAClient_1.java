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
public class JPAClient_1 {

	
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("b04_1");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Board newBoard;
		
		try {	// Entity CRUD, INSERT (CREATE)
			tx.begin();
			
			// -----------------------------------
			
			for(int i=0; i<30;i++) {
				
				newBoard = new Board();
				
				// 1. Non-Persistent State (NEW)
				newBoard.setTitle("안녕하세요... ^^ ..." + i);
				newBoard.setContent("CONTENT_"+i);
				newBoard.setWriter("Yoseph_"+i);
				
	//			newBoard.setCreateDate(new Date());								// 1st. method.
				newBoard.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));	// 2nd. method. (* Recommended *)
				
				newBoard.setCnt(0l);
				
				// 2. Persistent State (INSERT) & 1st. Cached.
				em.persist(newBoard);
				
			} // for : Persistence State & All `INSERT` SQL cached in the 1st. cache.

			// -----------------------------------
			
			// 3. INSERT SQLs Execution. (Like JDBC Batch Update)
			em.flush();

			// -----------------------------------
			
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		} // try-catch-finally
		
	} // main

} // end class
