package org.zerock.myapp.seq_10.persistence;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.seq_6.domain.Board;
import org.zerock.myapp.seq_8.persistence.BoardRepository;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class BoardRepositoryTests {
	@Autowired(required = false) private MockMvc mockMvc;
	@Autowired(required = true) private BoardRepository boardRepo;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assert this.mockMvc != null;
		log.info("\t+ this.mockMvc: {}", this.mockMvc);
		
		assert this.boardRepo != null;
		log.info("\t+ this.boardRepo: {}, type: {}", this.boardRepo, this.boardRepo.getClass().getName());
	} // beforeAll
	
	
//	@Disabled
	@Order(1)
//	@Test
	@RepeatedTest(1)
	@DisplayName("1. testInsertEachBoard")
	@Timeout(1L)
	void testInsertEachBoard() {		// OK
		log.trace("testInsertEachBoard() invoked.");

		// TRANSIENT -> MANAGED & flush
		IntStream.rangeClosed(1, 7).forEachOrdered(seq -> {
			log.trace("forEachOrdered({}) invoked.", seq);
			
			// -------------
			Board transientBoard = new Board();
			
			transientBoard.setTitle("Title_" + seq);
			transientBoard.setWriter("Yoseph");
			transientBoard.setContent("Content_" + seq);
			
			log.info("\t+ transientBoard: {}", transientBoard);

			// -------------
			Board managedBoard = this.boardRepo.save(transientBoard);		// INSERT, OK
			log.info("\t+ managedBoard: {}", managedBoard);
		});		// .forEachOrdered
	} // testInsertEachBoard
	
//	@Disabled
	@Order(2)
//	@Test
	@RepeatedTest(1)
	@DisplayName("2-1. testSelectEachBoardById")
	@Timeout(1L)
	void testSelectEachBoardById() {		// OK
		log.trace("testSelectEachBoardById() invoked.");

		IntStream.rangeClosed(1, 3).forEachOrdered(seq -> {
			log.trace("forEachOrdered({}) invoked.", seq);
			
			// -------------
			Optional<Board> foundBoard = this.boardRepo.findById(0L + seq);		// SELECT, OK

			// -------------
			Objects.requireNonNull(foundBoard);
			foundBoard.ifPresent(b -> {
				log.info("\t+ foundBoard: {}", b);
			});		// .ifPresent
		});		// .forEachOrdered
	} // testSelectEachBoardById
	
//	@Disabled
	@Order(3)
//	@Test
	@RepeatedTest(1)
	@DisplayName("2-2. testSelectAllBoardsByIds")
	@Timeout(1L)
	void testSelectAllBoardsByIds() {		// OK
		log.trace("testSelectAllBoardsByIds() invoked.");

		// -------------
		List<Long> ids = Arrays.<Long>asList(1L, 2L, 3L, 7L);
		log.info("\t+ ids: {}", ids);

		// -------------
		List<Board> foundBoards = this.boardRepo.findAllById(ids);
		foundBoards.forEach(log::info);
	} // testSelectAllBoardsByIds
	
//	@Disabled
	@Order(4)
//	@Test
	@RepeatedTest(1)
	@DisplayName("2-3. testSelectAllBoards")
	@Timeout(1L)
	void testSelectAllBoards() {		// OK
		log.trace("testSelectAllBoards() invoked.");

		List<Board> foundBoards = this.boardRepo.findAll();		// SELECT, OK		
		foundBoards.forEach(log::info);
	} // testSelectAllBoards
	
//	@Disabled
	@Order(5)
//	@Test
	@RepeatedTest(1)
	@DisplayName("4-1. testUpdateBoardById")
	@Timeout(1L)
	void testUpdateBoardById() {		// OK
		log.trace("testUpdateBoardById() invoked.");

		// -------------
		Optional<Board> foundBoard = this.boardRepo.findById(1L);		// SELECT, OK

		// -------------
		Objects.requireNonNull(foundBoard);
		foundBoard.ifPresentOrElse(b -> {
			log.info("\t+ ifPresentOrElse({}) invoked.", b);
			
			b.setCnt(100);
			this.boardRepo.save(b);																// UPDATE, OK
		}, () -> log.info("\t+ Not found."));		// .ifPresentOrElse
	} // testUpdateBoardById
	
//	@Disabled
	@Order(6)
//	@Test
	@RepeatedTest(1)
	@DisplayName("4-2. testUpdateAllBoardsByIds")
	@Timeout(1L)
	void testUpdateAllBoardsByIds() {		// OK
		log.trace("testUpdateAllBoardsByIds() invoked.");

		// -------------
		List<Long> ids = Arrays.<Long>asList(1L, 2L, 3L, 7L);
		log.info("\t+ ids: {}", ids);

		// -------------
		List<Board> foundBoards = this.boardRepo.findAllById(ids);
		foundBoards.forEach(b -> {
			log.trace("forEach({}) invoked.", b);
			
			b.setCnt(300);
		});		// .forEach

		// -------------
//		this.boardRepo.saveAll(foundBoards);						// UPDATE, OK
		this.boardRepo.saveAllAndFlush(foundBoards);		// UPDATE, OK
	} // testUpdateAllBoardsByIds
	
//	@Disabled
	@Order(7)
//	@Test
	@RepeatedTest(1)
	@DisplayName("5-1. testDeleteBoardById")
	@Timeout(1L)
	void testDeleteBoardById() {		// OK
		log.trace("testDeleteBoardById() invoked.");

		// -------------
		Optional<Board> foundBoard = this.boardRepo.findById(1L);		// SELECT, OK

		// -------------
		Objects.requireNonNull(foundBoard);
		foundBoard.ifPresent(b -> this.boardRepo.delete(b));					// DELETE
	} // testDeleteBoardById
	
//	@Disabled
	@Order(8)
//	@Test
	@RepeatedTest(1)
	@DisplayName("5-2. testDeleteAllBoardsByIds")
	@Timeout(1L)
	void testDeleteAllBoardsByIds() {		// OK
		log.trace("testDeleteAllBoardsByIds() invoked.");

		// -------------
		List<Long> ids = Arrays.<Long>asList(1L, 2L, 3L, 4L, 5L, 6L, 7L);
		log.info("\t+ ids: {}", ids);

		// -------------
		this.boardRepo.deleteAllById(ids);							// DELETE, OK
//		this.boardRepo.deleteAllByIdInBatch(ids);				// DELETE, OK
	} // testDeleteAllBoardsByIds
	
//	@Disabled
	@Order(9)
//	@Test
	@RepeatedTest(1)
	@DisplayName("5-3. testDeleteAllBoards")
	@Timeout(1L)
	void testDeleteAllBoards() {		// OK
		log.trace("testDeleteAllBoards() invoked.");

//		this.boardRepo.deleteAll(); 										// DELETE, OK
		this.boardRepo.deleteAllInBatch();							// DELETE, OK
	} // testDeleteAllBoards
	
} // end class
