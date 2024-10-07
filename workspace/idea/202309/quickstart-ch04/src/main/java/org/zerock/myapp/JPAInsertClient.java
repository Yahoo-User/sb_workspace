package org.zerock.myapp;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.zerock.myapp.domain.Board;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAInsertClient {
	
	
    public static void main( String[] args ) {
    	log.trace("main({}) invoked.", Arrays.toString(args));
    	
    	// Step1. EntityManagerFactory 생성
//    	@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("H2");
//    	@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("OCI-OCIDB");
//    	@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("OCI-ATP");
    	@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("AWS-RDS-MySQL");
    	
    	// Step2. EntityManager 획득 from EntityManagerFactory
    	@Cleanup EntityManager em = emf.createEntityManager();
    	
    	// Step3. EntityTransaction 획득 from EntityManager
    	EntityTransaction tx = em.getTransaction();
    	
    	try {
    		// Step4. Transaction Start
    		tx.begin();
    		
    		Board board = new Board();
    		
    		board.setTitle("NEW_TITLE");
    		board.setContent("NEW_CONTENT");
    		board.setWriter("Yoseph");
    		board.setCnt(0L);
    		
    		log.info("\t+ Before : {}", board);
    		
    		// Step5. To save a Entity into Persistence Context by EntityManager
    		em.persist(board);
    		log.info("\t+ After : {}", board);
    		
    		// Step6. Transaction Committed
    		tx.commit();
    	} catch(Exception e) {
    		// Step6. Transaction Rolled Back
    		tx.rollback();
    		
    		e.printStackTrace();
    	} finally {
    		;;
    	} // try-finally

    } // main
    
} // end class
