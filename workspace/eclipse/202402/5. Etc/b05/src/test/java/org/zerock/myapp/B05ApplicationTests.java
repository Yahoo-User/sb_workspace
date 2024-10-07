package org.zerock.myapp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.myapp.dao.BoardRepository;
import org.zerock.myapp.domain.Board;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@SpringBootTest
class B05ApplicationTests {
	
	@Autowired
	private BoardRepository dao;
	
	
	@BeforeAll
	static void beforeAll() {
		log.debug("beforeAll() invoked.");
		
	} // beforeAll
	
	@AfterAll
	static void afterAll() {
		log.debug("afterAll() invoked.");
		
	} // afterAll
	
	@BeforeEach
	void beforeEach() {
		log.debug("beforeEach() invoked.");
		
		assert this.dao != null;
		log.info("\t+ dao  : {}", dao);
		log.info("\t+ type : {}", dao.getClass().getName());
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.debug("afterEach() invoked.");
		
	} // afterEach
	
//	==============================

	@Test
	@Order(1)
	@DisplayName("testInsertBoard")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testInsertBoard() {
		log.debug("testInsertBoard() invoked.");
		
		for(int i=0; i<=30; i++) {
			Board board = new Board();
			
			board.setCnt(0l);
			board.setTitle("TITLE");
//			board.setWriter("WRITER");
			board.setContent("CONTENT");
			board.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
			
			dao.<Board>save(board);
		} // for
	} // testInsertBoard
	
	
	@Test
	@Order(2)
	@DisplayName("testGetBoard")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testGetBoard() {
		log.debug("testGetBoard() invoked.");
		
		Optional<Board> board=this.dao.findById(1l);
		log.info("\t+ board: {}", board);
		
		board.ifPresent(log::info);
	} // testGetBoard
	
	
	@Test
	@Order(3)
	@DisplayName("testFindAllBoards()")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testFindAllBoards() {
		log.debug("testFindAllBoards()() invoked.");
		
		Iterable<Board> iterable=this.dao.findAll();
		
//		for(Board board : iterable) {
//			log.info("\t+ board: {}", board);
//		} // enhanced for
		
		iterable.forEach(log::info);
	} // testFindAllBoards()
	
	
	@Test
	@Order(4)
	@DisplayName("testFindBoards")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testFindBoards() {
		log.debug("testFindBoards() invoked.");
		
		Iterable<Board> iterable = this.dao.findAllById(Arrays.asList(1l, 2l, 3l, 4l, 5l));
		
//		for(Board board : iterable) {
//			log.info("\t+ board: {}", board);
//		} // enhanced for
		
		iterable.forEach(log::info);
	} // testFindBoards
	
	
	@Test
	@Order(5)
	@DisplayName("testUpdateBoard")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testUpdateBoard() {
		log.debug("testUpdateBoard() invoked.");
		
		Board board = this.dao.findById(3l).get();
		
		assert board != null;
		
		board.setTitle("TITLE_MODIFIED");
		board.setCnt(board.getCnt()+1);
		
		this.dao.save(board);
	} // testUpdateBoard
	
	
	@Test
	@Order(6)
	@DisplayName("testTotalCount")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testTotalCount() {
		log.debug("testTotalCount() invoked.");
		
		log.info("\t+ total count: {}", this.dao.count());
	} // testTotalCount
	
	
	@Test
	@Order(7)
	@DisplayName("testDeleteBoard")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testDeleteBoard() {
		log.debug("testDeleteBoard() invoked.");
		
		this.dao.deleteById(1l);
	} // testDeleteBoard
	
	
	@Test
	@Order(8)
	@DisplayName("testDeleteBoards")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testDeleteBoards() {
		log.debug("testDeleteBoards() invoked.");
		
		this.dao.deleteAllById(Arrays.asList(3l, 33l));
	} // testDeleteBoards
	
	
	@Test
	@Order(9)
	@DisplayName("testDeleteAllBoards")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testDeleteAllBoards() {
		log.debug("testDeleteAllBoards() invoked.");
		
		this.dao.deleteAll();
	} // testDeleteAllBoards
	

} // end class
