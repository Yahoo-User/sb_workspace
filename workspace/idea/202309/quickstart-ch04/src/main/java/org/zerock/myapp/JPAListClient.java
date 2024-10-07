package org.zerock.myapp;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.zerock.myapp.domain.Board;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPAListClient {

	
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));

    	// Step1. EntityManagerFactory 생성
//		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("H2");
//		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("OCI-OCIDB");
//		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("OCI-ATP");
		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("AWS-RDS-MySQL");

    	// Step2. EntityManager 획득 from EntityManagerFactory
		@Cleanup EntityManager em = emf.createEntityManager();

		// Step3. 게시판 목록조회
		String jpql = "SELECT b FROM Board b ORDER BY b.seq DESC";
		
		TypedQuery<Board> typedQuery = em.<Board>createQuery(jpql, Board.class);
		log.info("\t+ typedQuery: {}", typedQuery);
		 
		List<Board> list = typedQuery.getResultList();
		list.forEach(log::info);
	} // main

} // end class
