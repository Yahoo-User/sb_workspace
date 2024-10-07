package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.myapp.domain.Board;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
class BoardRepositoryTests {
	@Autowired private BoardRepository boardRepository;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assertNotNull(this.boardRepository);
		log.info("\t+ this.boardRepository: {}", this.boardRepository);
	} // beforeAll

//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("testInsertBoard")
	@Timeout(1L)
	void testInsertBoard() {
		log.trace("testInsertBoard() invoked.");

		Board board = new Board();
		board.setTitle("TITLE");
		board.setWriter("Yoseph");
		board.setContent("CONTENT");
		board.setCnt(0L);

		this.boardRepository.save(board);
		log.info("\t+ board: {}", board);
	} // testInsertBoard

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(2)
	@DisplayName("testGetBoard")
	@Timeout(1L)
	void testGetBoard() {
		log.trace("testGetBoard() invoked.");

		Optional<Board> optional = this.boardRepository.findById(5L);
		if(optional.isPresent()) {
			Board foundBoard = optional.get();
			log.info("\t+ foundBoard: {}", foundBoard);
		} // if
	} // testGetBoard

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(3)
	@DisplayName("testUpdateBoard")
	@Timeout(1L)
	void testUpdateBoard() {
		log.trace("testUpdateBoard() invoked.");

		Board foundBoard = this.boardRepository.findById(5L).orElse(null); // MANAGED

		Objects.requireNonNull(foundBoard);
		foundBoard.setTitle("MODIFIED_TITLE");

		this.boardRepository.save(foundBoard);	// flush
		log.info("\t+ foundBoard: {}", foundBoard);
	} // testUpdateBoard

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(4)
	@DisplayName("testDeleteBoard")
	@Timeout(1L)
	void testDeleteBoard() {
		log.trace("testDeleteBoard() invoked.");

		this.boardRepository.deleteById(3L);

		Board board1L = this.boardRepository.findById(3L).orElse(null);
		log.info("\t+ board1L: {}", board1L);
	} // testDeleteBoard

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(5)
	@DisplayName("testFindByTitleContainingAndPaging")
	@Timeout(1L)
	void testFindByTitleContainingAndPaging() {
		log.trace("testFindByTitleContainingAndPaging() invoked.");

		int currPage = 0;			// started with 0.
		int amount = 20;			// lines per page.
		PageRequest paging = PageRequest.of(currPage, amount);

		String title = "TITLE";

		List<Board> list = this.boardRepository.findByTitleContaining(title, paging);

		Objects.requireNonNull(list);
		list.forEach(log::info);
	} // testFindByTitleContainingAndPaging

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(6)
	@DisplayName("testFindByTitleContainingOrderBySeqDesc")
	@Timeout(1L)
	void testFindByTitleContainingOrderBySeqDesc() {
		log.trace("testFindByTitleContainingOrderBySeqDesc() invoked.");

		int currPage = 0;			// started with 0.
		int amount = 20;			// lines per page.
		PageRequest paging = PageRequest.of(currPage, amount);

		String title = "TITLE";

		List<Board> list = this.boardRepository.findByTitleContainingOrderBySeqDesc(title, paging);

		Objects.requireNonNull(list);
		list.forEach(log::info);
	} // testFindByTitleContainingOrderBySeqDesc

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(7)
	@DisplayName("testFindByWriter")
	@Timeout(1L)
	void testFindByWriter() {
		log.trace("testFindByWriter() invoked.");

		PageRequest paging = PageRequest.of(0, 20, Sort.Direction.DESC, "seq");

		List<Board> list = this.boardRepository.findByWriter("Yoseph", paging);

		Objects.requireNonNull(list);
		list.forEach(log::info);
	} // testFindByWriter


	//    @Enabled
	@Tag("fast")
	@Test
	@Order(8)
	@DisplayName("testFindByWriterContaining")
	@Timeout(1L)
	void testFindByWriterContaining() {
		log.trace("testFindByWriterContaining() invoked.");

		Pageable paging = PageRequest.of(3, 20, Sort.Direction.DESC, "seq");

		Page<Board> page = this.boardRepository.findByWriterContaining("seph", paging);

		log.info("\t01. number: {}", page.getNumber());
		log.info("\t02. size: {}", page.getSize());
		log.info("\t03. totalPages: {}", page.getTotalPages());
		log.info("\t04. numberOfElements: {}", page.getNumberOfElements());
		log.info("\t05. hasPrevious: {}", page.hasPrevious());
		log.info("\t06. hasNext: {}", page.hasNext());
		log.info("\t07. isLast: {}", page.isLast());
		log.info("\t08. hasContent: {}", page.hasContent());
		log.info("\t09. totalAmount: {}", page.getTotalElements());
		log.info("\t10. sort: {}", page.getSort());
		log.info("\t11. hasContent: {}", page.hasContent());
		log.info("\t12. nextPageable: {}", page.nextPageable());
		log.info("\t13. previousPageable: {}", page.previousPageable());

		List<Board> list = page.getContent();

		Assertions.assertNotNull(list);
		list.forEach(log::info);
	} // testFindByWriterContaining

} // end class
