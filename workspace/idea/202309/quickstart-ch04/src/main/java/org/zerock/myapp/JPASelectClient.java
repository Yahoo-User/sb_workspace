package org.zerock.myapp;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.zerock.myapp.domain.Board;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JPASelectClient {

	
	public static void main(String[] args) {
		log.trace("main({}) invoked.", Arrays.toString(args));

    	// Step1. EntityManagerFactory 생성
//		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("H2");
//		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("OCI-OCIDB");
//		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("OCI-ATP");
		@Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory("AWS-RDS-MySQL");

    	// Step2. EntityManager 획득 from EntityManagerFactory
		@Cleanup EntityManager em = emf.createEntityManager();

		// Step3. PK 로 검색
		Board foundBoard = em.<Board>find(Board.class, 1L);
		log.info("\t+ foundBoard: {}", foundBoard);
		
//		---
		
		// Step4. 특정 PK 값 가진 행이 포함되어 있는지 확인
		// PK 만 바꾸어도 됨 => PK 값으로 포함여부 판단 => 하지만, 엔티티 내용은 변경없음 (***)
		if(foundBoard != null) {
			foundBoard.setSeq(2L);
			boolean isContains = em.contains(foundBoard);
			
			log.info("\t+ isContains: {}", isContains);
			log.info("\t+ foundBoard: {}", foundBoard);
		} // if
	} // main

} // end class
