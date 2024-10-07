package org.zerock.myapp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.zerock.myapp.dao.BoardRepository;
import org.zerock.myapp.domain.Board;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@SpringBootTest
public class QueryMethodTests {
	
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
		log.info("\t+ 1. dao  : {}", this.dao);
		log.info("\t+ 2. type : {}", this.dao.getClass().getName());
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.debug("afterEach() invoked.");
	
	} // afterEach
	
//	===============================
	
	
	@Test
	@Order(1)
	@DisplayName("prepareData")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void prepareData() {
		log.debug("prepareData() invoked.");
		
		for(int i=1; i<=10; i++) {
			Board b = new Board();
			
			b.setCnt(0l);
			b.setTitle("TITLE_"+i);
//			b.setWriter("WRITER_"+i);
			b.setContent("CONTENT_"+i);
			b.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
			
			this.dao.save(b);
		} // for
	} // prepareData
	
	
	@Test
	@Order(2)
	@DisplayName("testFindByTitle")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testFindByTitle() {
		log.debug("testFindByTitle() invoked.");
		
		List<Board> board = this.dao.findByTitle("TITLE_10");
		board.forEach(log::info);
	} // contextLoads
	
	
	@Test
	@Order(3)
	@DisplayName("findByTitleContaining")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testFindByTitleContaining() {
		log.debug("findByTitleContaining() invoked.");
		
		List<Board> board = this.dao.findByTitleContaining("TLE_3");
		board.forEach(log::info);
	} // testFindByTitleContaining
	
	
	@Test
	@Order(4)
	@DisplayName("testFindByTitleLike")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testFindByTitleLike() {
		log.debug("testFindByTitleLike() invoked.");
		
//		List<Board> board = this.dao.findByTitleLike("TLE_3");		// No founds.
		List<Board> board = this.dao.findByTitleLike("TITLE_3");	// 1 found.
		
		board.forEach(log::info);
	} // testFindByTitleLike
	
	
	@Test
	@Order(5)
	@DisplayName("testFindByTitleContainingOrContentContaining")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testFindByTitleContainingOrContentContaining() {
		log.debug("testFindByTitleContainingOrContentContaining() invoked.");
		
		List<Board> board = this.dao.findByTitleContainingOrContentContaining("TLE_2", "CONTENT_3");	// OK
		
		// java.lang.IllegalStateException: Method expects at least 2 arguments but only found 1.
		//                                  This leaves an operator of type CONTAINING for property content unbound.
//		List<Board> board = this.dao.findByTitleContainingOrContentContaining("TLE_2");					// XX
		
		board.forEach(log::info);
	} // testFindByTitleContainingOrContentContaining
	
	
	@Test
	@Order(6)
	@DisplayName("testFindByTitleContainingOrderBySeqDesc")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testFindByTitleContainingOrderBySeqDesc() {
		log.debug("testFindByTitleContainingOrderBySeqDesc() invoked.");
		
		List<Board> board = this.dao.findByTitleContainingOrderBySeqDesc("TLE_7");
//		board.forEach(b -> log.info(b));
		board.forEach(log::info);
	} // testFindByTitleContainingOrderBySeqDesc
	
	
	@Test
	@Order(7)
	@DisplayName("testFindByTitleContainingPaging")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testFindByTitleContainingPaging() {
		log.debug("testFindByTitleContainingPaging() invoked.");
		
		int page = 2;
		int size = 5;
		log.info("\t+ page: {}, size: {}", page, size);
		
		// PageRequest implements Pageable           (***)
		// PageRequest.of(page, size): PageRequest   (***)
		Pageable paging = PageRequest.of(page, size);
		
//		--------------------------------
		
		List<Board> board = this.dao.findByTitleContaining("TITLE", paging);
		board.forEach(log::info);
	} // testFindByTitleContainingPaging
	
	
	@Test
	@Order(8)
	@DisplayName("testFindByTitleContainingPagingAndSorting")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testFindByTitleContainingPagingAndSorting() {
		log.debug("testFindByTitleContainingPagingAndSorting() invoked.");
		
		int page = 2;
		int size = 5;
		
		log.info("\t+ page: {}, size: {}", page, size);
		
//		----------------------------
		
//		Pageable paging = PageRequest.of(page, size, Sort.Direction.DESC, "seq", "createDate");
		Pageable paging = PageRequest.of(page, size, Direction.DESC, "seq", "createDate");
		
		List<Board> board = this.dao.findByTitleContaining("TITLE", paging);
		board.forEach(log::info);
	} // testFindByTitleContainingPagingAndSorting
	
	
	@Test
	@Order(9)
	@DisplayName("testFindByContentContaining")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testFindByContentContaining() {
		log.debug("testFindByContentContaining() invoked.");
		
		int page = 3, size = 10;
		log.info("\t1. page: {}, size: {}", page, size);
		
//		Pageable paging = PageRequest.of(page, size, Sort.Direction.DESC, "seq");
		Pageable paging = PageRequest.of(page, size, Direction.DESC, "seq");
		Page<Board> currPage = this.dao.findByContentContaining("CONTENT", paging);
		
		log.info("\t2. paging   : {}", paging);
		log.info("\t3. currPage : {}", currPage);
		
//		-------------------------------
		
		log.info("\t4. Page size   : {}", currPage.getSize());
		log.info("\t5. Total page  : {}", currPage.getTotalPages());
		log.info("\t6. Total count : {}", currPage.getTotalElements());
		log.info("\t7. Next: {}", currPage.nextPageable());
		
		currPage.getContent().forEach(log::info);
	} // testFindByContentContaining
	

} // end class
