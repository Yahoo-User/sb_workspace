package org.zerock.myapp;

import java.util.List;
import java.util.Objects;
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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
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

@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
public class QueryAnnotationTests {
	
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
		
//		assert this.dao != null;
		Objects.requireNonNull(this.dao);
		
		log.info("\t+ dao: {}, type: {}", this.dao, this.dao.getClass().getName());
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.debug("afterEach() invoked.");
		
	} // afterEach
	
//	---------------------------------------
	
	@Test
	@Order(1)
	@DisplayName("testQueryAnnotationTest1")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testQueryAnnotationTest1() {
		log.debug("testQueryAnnotationTest1() invoked.");
		
		List<Board> board = this.dao.queryAnnotationTest1("TITLE_1");
		Objects.requireNonNull(board);
		
		board.forEach(log::info);
	} // testQueryAnnotationTest1
	
	
	@Test
	@Order(2)
	@DisplayName("testQueryAnnotationTest2")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testQueryAnnotationTest2() {
		log.debug("testQueryAnnotationTest2() invoked.");
		
		List<Board> board = this.dao.queryAnnotationTest2("CONTENT_7");
		board.forEach(log::info);
	} // testQueryAnnotationTest2

	
//	@Test
//	@Order(3)
//	@DisplayName("testQueryAnnotationTest3")
//	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
//	void testQueryAnnotationTest3() {
//		log.debug("testQueryAnnotationTest3() invoked.");
//		
//		List<Object[]> board = this.dao.queryAnnotationTest3("TITLE");
////		board.forEach(log::info);
//		board.forEach(b -> log.info(Arrays.toString(b)));
//	} // testQueryAnnotationTest3
	
	
//	@Test
//	@Order(4)
//	@DisplayName("testQueryAnnotationTest4")
//	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
//	void testQueryAnnotationTest4() {
//		log.debug("testQueryAnnotationTest4() invoked.");
//		
//		List<Object[]> board = this.dao.queryAnnotationTest4("WRITER_2");
////		board.forEach(log::info);
//		board.forEach(b -> log.info(Arrays.toString(b)));
//	} // testQueryAnnotationTest4
	
	
	@Test
	@Order(5)
	@DisplayName("testQueryAnnotationTest5")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testQueryAnnotationTest5() {
		log.debug("testQueryAnnotationTest5() invoked.");
		
		int page = 3, size = 10;
		log.info("\t+ page   : {}, size: {}", page, size);
		
		Pageable paging = PageRequest.of(page, size, Direction.DESC, "seq");
//		Pageable paging = PageRequest.of(page, size, Sort.Direction.DESC, "seq");
		log.info("\t+ paging : {}", paging);
		
//		-----------------------------
		
		List<Board> board = this.dao.queryAnnotationTest5(paging);
		board.forEach(log::info);
	} // testQueryAnnotationTest5
	

} // end class
