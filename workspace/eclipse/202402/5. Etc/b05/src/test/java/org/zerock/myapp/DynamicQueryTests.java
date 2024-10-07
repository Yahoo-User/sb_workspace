package org.zerock.myapp;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.zerock.myapp.dao.DynamicBoardRepository;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.QBoard;

import com.querydsl.core.BooleanBuilder;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@SpringBootTest(webEnvironment=WebEnvironment.MOCK) // Default
public class DynamicQueryTests {
	
	@Setter(onMethod_= {@Autowired})
	private DynamicBoardRepository dao;
	
	
	
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
		log.info("\t+ dao : {}, type: {}", this.dao, this.dao.getClass().getName());
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.debug("afterEach() invoked.");
		
	} // afterEach
	
//	------------------------------
	
	@Test
	@Order(1)
	@DisplayName("testDynamicQuery")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testDynamicQuery() {
		log.debug("testDynamicQuery() invoked.");
		
		String searchCondition = "CONTENT";
		String searchKeyword = "CONTENT_";
		
		BooleanBuilder builder = new BooleanBuilder();
		log.info("\t+ builder: {}", builder);
		
		QBoard qboard = QBoard.board;
		log.info("\t+ qboard: {}, type: {}", qboard, qboard.getClass().getName());
		
		if(searchCondition.equals("TITLE")) {
			
			builder.and(qboard.title.like("%"+searchKeyword+"%"));
			
		} else if(searchCondition.equals("CONTENT")) {
			
//			builder.and(qboard.content.like("%"+searchKeyword+"%")).not();	// OK
			builder.andNot(qboard.content.like("%"+searchKeyword+"%"));		// Ditto.
			
		} // if-else if
		
		int page = 0, size = 10;
		
		Pageable paging = PageRequest.of(page, size, Direction.DESC, "seq");
		log.info("\t+ paging: {}", paging);
		
		Page<Board> boardList = this.dao.findAll(builder, paging);
		boardList.forEach(log::info);
	} // testDynamicQuery
	
	
	@Test
	@Order(2)
	@DisplayName("testDynamicQuery2")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testDynamicQuery2() {
		log.debug("testDynamicQuery2() invoked.");

		QBoard qboard = QBoard.board;
		BooleanBuilder builder = new BooleanBuilder();
		
//		NumberPath<Long> seqPath = qboard.seq;
//		builder.and(seqPath.between(1l, 100l));
		
		builder.and(qboard.seq.between(1l, 100l));

		int page=0, size=10;
		Pageable paging = PageRequest.of(page, size, Direction.DESC, "seq");
		
		Page<Board> boardList = this.dao.findAll(builder, paging);
		boardList.forEach(log::info);
	} // testDynamicQuery2
	

} // end class
