package org.zerock.myapp.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.myapp.domain.Board;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

// `@Commit` is a test annotation that is used to indicate
// that a test-managed transaction should be committed after the test method has completed.
@Commit

@SpringBootTest		// With mocking embedded WAS.
//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)	// With embedded WAS.

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardRepositoryTests {
	@Autowired private BoardRepository boardRepo;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.boardRepo);
		log.info("\t+ this.boardRepo: {}, type: {}", this.boardRepo, this.boardRepo.getClass().getName());
	} // beforeAll
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testInsertBoard")
	@Timeout(1)
	void testInsertBoard() {
		log.trace("testInsertBoard() invoked.");
		
		Board newBoard = new Board();
		newBoard.setTitle("NEW_TITLE");
//		newBoard.setWriter("Yoseph");
		newBoard.setContent("NEW_CONTENT");
		
		this.boardRepo.save(newBoard);
		log.info("\t+ newBoard: {}", newBoard);
	} // testInsertBoard
	
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testSelectBoard")
	@Timeout(1)
	void testSelectBoard() {
		log.trace("testSelectBoard() invoked.");
	
		Optional<Board> optional = this.boardRepo.findById(2L);
		Board board = optional.get();

		log.info("\t+ board: {}", board);
	} // testSelectBoard
	
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("testUpdateBoard")
	@Timeout(1)
	void testUpdateBoard() {
		log.trace("testUpdateBoard() invoked.");
		
		Board board = this.boardRepo.findById(12L).get();
		
		Objects.requireNonNull(board);
		board.setTitle("UPDATE_TITLE");
		
		this.boardRepo.save(board);
	} // testUpdateBoard
	
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("testDeleteBoard")
	@Timeout(1)
	void testDeleteBoard() {
		log.trace("testDeleteBoard() invoked.");
		
		this.boardRepo.deleteById(3L);
	} // testDeleteBoard
	
	
} // end class
