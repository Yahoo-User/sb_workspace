package org.zerock.myapp.seq_12.persistence;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.seq_11.persistence.BoardRepositoryWithQueryMethods;
import org.zerock.myapp.seq_6.domain.Board;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc

@SpringBootTest(
	webEnvironment = WebEnvironment.MOCK, 
	
//	properties = "spring.jpa.hibernate.ddl-auto=create"
	properties = "spring.jpa.hibernate.ddl-auto=update"
)

//@SpringBootTest(
//	webEnvironment = WebEnvironment.DEFINED_PORT, 
//
//	properties = "spring.jpa.hibernate.ddl-auto=create"
//	properties = "spring.jpa.hibernate.ddl-auto=update"
//)
public class BoardRepositoryWithQueryMethodsTests_1 {
	@Setter(onMethod_ = @Autowired(required = false)) private MockMvc mockMvc;
	@Setter(onMethod_ = @Autowired(required = true))  private BoardRepositoryWithQueryMethods boardRepo;
		
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		// ---------
		assert this.mockMvc != null; 
		assert this.boardRepo != null; 
	} // beforeAll
	
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("0. prepareTestData")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void prepareTestData() {	
		log.trace("prepareTestData() invoked.");

		// ---------
		this.boardRepo.deleteAllInBatch();

		// ---------
		IntStream.rangeClosed(1, 200).forEachOrdered(seq -> {
			log.trace("  forEachOrdered(%03d) invoked.".formatted(seq));

			Board transientBoard = new Board();
			
			transientBoard.setTitle("Title_"+seq);
			transientBoard.setWriter("Yoseph");
			transientBoard.setContent("Content_"+seq);

			this.boardRepo.save(transientBoard);
		});		// .forEachOrdered
	} // prepareTestData
	
//	@Disabled
	@Order(2)
//	@Test
	@RepeatedTest(1)
	@DisplayName("1. testFindBoardBySeq")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindBoardBySeq() {
		log.trace("testFindBoardBySeq() invoked.");

		// ---------
		int randomSeq = (int) (Math.random() * (200 - 1)) + 1;
		Long searchSeq = 0L + randomSeq;
		
		@Cleanup("clear")
		List<Board> foundBoards = this.boardRepo.findBoardBySeq(searchSeq);
		
		Objects.requireNonNull(foundBoards);
		foundBoards.forEach(b -> {
			log.trace("forEach({}) invoked.", b);
			
			b.setCnt(randomSeq);
			this.boardRepo.save(b);
		});		// .forEach
	} // testFindBoardBySeq
	
//	@Disabled
	@Order(3)
//	@Test
	@RepeatedTest(1)
	@DisplayName("2. testFindBoardByTitle")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindBoardByTitle() {
		log.trace("testFindBoardByTitle() invoked.");

		// ---------
		int randomSeq = (int) (Math.random() * (200 - 1)) + 1;
		String searchTitle = "Title_" + randomSeq;
		
		@Cleanup("clear")
		List<Board> foundBoards = this.boardRepo.findBoardByTitle(searchTitle);
		
		Objects.requireNonNull(foundBoards);
		foundBoards.forEach(b -> {
			log.trace("forEach({}) invoked.", b);

			b.setCnt(randomSeq);
			this.boardRepo.save(b);
		});		// .forEach
	} // testFindBoardByTitle
	
//	@Disabled
	@Order(4)
//	@Test
	@RepeatedTest(1)
	@DisplayName("3. testFindBoardByWriter")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindBoardByWriter() {
		log.trace("testFindBoardByWriter() invoked.");

		// ---------
		String searchWriter = "Yoseph";
		
		@Cleanup("clear")
		List<Board> foundBoards = this.boardRepo.findBoardByWriter(searchWriter);
		
		Objects.requireNonNull(foundBoards);
		foundBoards.forEach(b -> {
			log.trace("forEach({}) invoked.", b);

			int randomSeq = (int) (Math.random() * (200 - 1)) + 1;
			b.setCnt(randomSeq);
			
			this.boardRepo.save(b);
		});		// .forEach
	} // testFindBoardByWriter
	
//	@Disabled
	@Order(5)
//	@Test
	@RepeatedTest(1)
	@DisplayName("4. testFindBoardByContent")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindBoardByContent() {
		log.trace("testFindBoardByContent() invoked.");

		// ---------
		int randomSeq = (int) (Math.random() * (200 - 1)) + 1;
		String searchContent = "Content_" + randomSeq;
		
		@Cleanup("clear")
		List<Board> foundBoards = this.boardRepo.findBoardByContent(searchContent);
		
		Objects.requireNonNull(foundBoards);
		foundBoards.forEach(b -> {
			log.trace("forEach({}) invoked.", b);

			b.setContent("Modified " + searchContent);
			this.boardRepo.save(b);
		});		// .forEach
	} // testFindBoardByContent
	
//	@Disabled
	@Order(6)
//	@Test
	@RepeatedTest(1)
	@DisplayName("5. testFindBoardByCnt")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindBoardByCnt() {
		log.trace("testFindBoardByCnt() invoked.");

		// ---------
		int randomSeq = (int) (Math.random() * (200 - 1)) + 1;
		int searchCnt = randomSeq;
		
		@Cleanup("clear")
		List<Board> foundBoards = this.boardRepo.findBoardByCnt(searchCnt);
		
		Objects.requireNonNull(foundBoards);
		foundBoards.forEach(b -> {
			log.trace("forEach({}) invoked.", b);
			
			b.setTitle("Modified Title_" + randomSeq);
			this.boardRepo.save(b);
		});		// .forEach
	} // testFindBoardByCnt
	
	

	
} // end class
