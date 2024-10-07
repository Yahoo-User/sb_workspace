package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.persistence.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

/*
 * ===================================
 * `@Commit` Annotation
 * ===================================
 * `@Commit` is a test annotation that is used to indicate that a test-managed transaction should be committed
 * after the test method has completed.
 *
 */
@Commit

/*
 * ===================================
 * `@AutoConfigureMockMvc` Annotation
 * ===================================
 * Annotation that can be applied to a test class to enable and configure auto-configuration of `MockMvc`.
 *
 */
@AutoConfigureMockMvc

// With mocking Tomcat servlet container.
@SpringBootTest
// With Tomcat embedded servlet container.
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BoardServiceTests {
	@Setter(onMethod_ = @Autowired)
	private MockMvc mockMvc;
	@Setter(onMethod_ = @Autowired)
	private BoardService boardService;
	@Setter(onMethod_ = @Autowired)
	private MemberRepository memberRepository;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assertNotNull(this.boardService);
		log.info("\t+ this.boardService: {}", this.boardService);

		assertNotNull(this.memberRepository);
		log.info("\t+ this.memberRepository: {}", this.memberRepository);
	} // beforeAll

//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("testGetBoardList")
	@Timeout(1L)
	void testGetBoardList() throws ServiceException {
		log.trace("testGetBoardList() invoked.");

		PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
		Page<Board> requestPage = this.boardService.getBoardList(pageRequest);
		requestPage.forEach(log::info);
	} // testGetBoardList

	@Commit
	//	@Disabled
	@Tag("fast")
	@Test
	@Order(2)
	@DisplayName("testInsert")
	@Timeout(1L)
	void testInsert() throws ServiceException {
		log.trace("testInsert() invoked.");

		Member admin = this.memberRepository.findById("admin").orElseThrow();
		log.info("\t+ admin: {}", admin);

		Board board = new Board();
		board.setTitle("NEW_TITLE");
		board.setContent("NEW_CONTENT");
		board.setMember(admin);

		Integer affectedRows = this.boardService.insert(board);
		assertThat(affectedRows).isEqualTo(1);

		log.info("\t+ affectedRows: {}", affectedRows);
		log.info("\t+ board: {}", board);
	} // testInsert

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(3)
	@DisplayName("testSelect")
	@Timeout(1L)
	void testSelect() throws ServiceException {
		log.trace("testSelect() invoked.");

		Board board = new Board();
		board.setSeq(152L);

		Board foundBoard = this.boardService.select(board);

		assertThat(foundBoard).isNotNull();
		log.info("\t+ member: {}, foundBoard: {}", foundBoard.getMember(), foundBoard);
	} // testSelect

	@Commit
	//	@Disabled
	@Tag("fast")
	@Test
	@Order(4)
	@DisplayName("testUpdate")
	@Timeout(1L)
	void testUpdate() throws ServiceException {
		log.trace("testUpdate() invoked.");

		Board board = new Board();
		board.setSeq(50L);
		board.setTitle("TITLE_UPDATED2");
		board.setContent("CONTENT_UPDATED2");
		board.setCnt(2L);

		Integer affectedRows = this.boardService.update(board);

		assertThat(affectedRows).isEqualTo(1);
		log.info("\t+ affectedRows: {}", affectedRows);
	} // testUpdate

	@Commit
	//	@Disabled
	@Tag("fast")
	@Test
	@Order(5)
	@DisplayName("testDelete")
	@Timeout(1L)
	void testDelete() throws ServiceException {
		log.trace("testDelete() invoked.");

		Board board = new Board();
		board.setSeq(152L);

		Integer affectedRows = this.boardService.delete(board);

		assertThat(affectedRows).isEqualTo(1);
		log.info("\t+ affectedRows: {}", affectedRows);
	} // testDelete

} // end class
