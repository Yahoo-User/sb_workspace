package org.zerock.myapp;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.zerock.myapp.domain.Board;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAClient_4 {
	
	
	public static void main(String[] args) {
		log.debug("main({}) invoked", Arrays.toString(args));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("b04_1");
		EntityManager em = emf.createEntityManager();
		
		try {	// Entity CRUD, READ (SELECT)
			
			String jpql = "SELECT * FROM tbl_board WHERE seq > 10";
			Query query = em.createNativeQuery(jpql, Board.class);
			log.info("\t+ query: {}", query);
			
			@SuppressWarnings("unchecked")
			List<Board> boards = (List<Board>) query.getResultList();
			
			boards.forEach(log::info);
		} catch(Exception e) {
			throw e;
		} finally {
			em.close();
			emf.close();
		} // try-catch-finally
		
		
	} // main

} // end class
