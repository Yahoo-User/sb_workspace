package org.zerock.myapp.seq_3.persistence;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.myapp.seq_1.domain.Board;
import org.zerock.myapp.seq_1.domain.QBoard;
import org.zerock.myapp.seq_2.persistence.BoardRepositoryWithDynamicQuery;
import org.zerock.myapp.util.RandomNumberGenerator;

import com.querydsl.core.BooleanBuilder;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@AutoConfigureMockMvc

@SpringBootTest(
	webEnvironment = WebEnvironment.MOCK,
//	webEnvironment = WebEnvironment.DEFINED_PORT,
//	properties = "spring.jpa.hibernate.ddl-auto=create"
	properties = "spring.jpa.hibernate.ddl-auto=update"
)
class BoardRepositoryWithDynamicQueryTests {
	@Autowired(required = true)
	private BoardRepositoryWithDynamicQuery boardRepo;

	
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
		log.info("  + this.boardRepo: {}", this.boardRepo);
	} // postConstruct
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		assert this.boardRepo != null;
	} // beforeAll
	
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. prepareData")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void prepareData() {
		log.trace("prepareData() invoked.");

		// -------------
		String[] writers = { "Yoseph", "Trinity", "Pyramid" };
		
		// -------------
		IntStream.rangeClosed(1, 200).forEachOrdered(seq -> {
			log.trace("forEachOrdered({}) invoked.", seq);

			// -----
			Board transientBoard = new Board();
			
			transientBoard.setSeq(0L + seq);
			transientBoard.setTitle("Title_%03d".formatted(seq));
			transientBoard.setContent("Content_%03d".formatted(seq));
			
			int writerIndex = RandomNumberGenerator.generateInt(0, 3); 
			transientBoard.setWriter(writers[writerIndex]);

			// -----
			try { Thread.sleep(1000L * RandomNumberGenerator.generateInt(1, 5)); } 
			catch (InterruptedException _ignored) {}
			
			// -----
			this.boardRepo.save(transientBoard);
		});		// .forEachOrdered
	} // prepareData
	
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("2. testDynamicQueryWithQuerydsl")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testDynamicQueryWithQuerydsl() {
		log.trace("testDynamicQueryWithQuerydsl() invoked.");

		// -------------
//		String 	searchCondition = "TITLE", searchPattern = "%tle_%3%";
//		String	searchCondition = "CONTENT", searchPattern ="Content__7%";
		String searchCondition = "WRITER", searchPattern = "Yoseph";

		BooleanBuilder builder = new BooleanBuilder();			// Querydsl Predicate Generator.
		QBoard qBoard = QBoard.board;									// Querydsl QType Generator.

		// -------------
		switch(searchCondition) {
			case "TITLE"	-> builder.and( qBoard.title.like(searchPattern) );
			case "CONTENT" -> builder.and( qBoard.content.like(searchPattern) );
			case "WRITER" -> builder.and( qBoard.writer.like(searchPattern) );
		} // switch expression
		
		// -------------
//		Pageable paging = PageRequest.of(0, 20);													// Spring Data
		Pageable paging = PageRequest.of(0, 7, Sort.Direction.DESC, "seq");	// Spring Data
		
		/**
			    ------------------------
			    where writer like ? escape '!' 
			    order by board_id desc 
			    offset ? rows 
			    fetch first ? rows only
			    ------------------------
			    select count(board_id) 
			    from board 
			    where writer like ? escape '!'
		 */
		
		Page<Board> foundPage = this.boardRepo.findAll(builder, paging);		// QuerydslPredicateExecutor
		log.info("  + foundPage: {}, totalElements: {}", foundPage, foundPage.getTotalElements());

		// -------------
		foundPage.forEach(log::info);
	} // testDynamicQueryWithQuerydsl
	
	

} // end class


