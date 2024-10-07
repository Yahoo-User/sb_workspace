package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.domain.Role;

import java.util.List;
import java.util.Objects;

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
class BoardRepositoryTests {
	@Setter(onMethod_ = @Autowired)
	private MockMvc mockMvc;
	@Setter(onMethod_ = @Autowired)
	private BoardRepository boardRepository;
	@Setter(onMethod_ = @Autowired)
	private MemberRepository memberRepository;
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder passwordEncoder;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assertNotNull(this.mockMvc);
		log.info("\t+ this.mockMvc: {}", this.mockMvc);

		Objects.requireNonNull(this.boardRepository);
		log.info("\t+ this.boardRepository: {}", this.boardRepository);

		assert this.memberRepository != null;
		log.info("\t+ this.memberRepository: {}", this.memberRepository);

		Objects.requireNonNull(this.passwordEncoder);
		log.info("\t+ this.passwordEncoder: {}", this.passwordEncoder);
	} // beforeAll

//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("insertDummyDataBeforeAddingPasswordEncoderBean")
	@Timeout(3L)
	void insertDummyDataBeforeAddingPasswordEncoderBean() {
		log.trace("insertDummyDataBeforeAddingPasswordEncoderBean() invoked.");

		Member member1 = new Member();
			member1.setId("member");
			member1.setName("회원");
			member1.setPassword("member123");
			member1.setRole(Role.ROLE_MEMBER);
			member1.setEnabled(true);

		this.memberRepository.save(member1);
		log.info("\t+ member1: {}", member1);


		Member member2 = new Member();
			member2.setId("admin");
			member2.setName("관리자");
			member2.setPassword("admin123");
			member2.setRole(Role.ROLE_ADMIN);
			member2.setEnabled(true);

		this.memberRepository.save(member2);
		log.info("\t+ member2: {}", member2);


		for(int i=1; i<=30; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle(member1.getName() + "가 등록한 글 " + i);
			board.setContent(member1.getName() + "가 등록한 게시글 " + i);

			this.boardRepository.save(board);
			log.info("\t+ board: {}", board);
		} // for

		for(int i=1; i<=23; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle(member2.getName() + "가 등록한 글 " + i);
			board.setContent(member2.getName() + "가 등록한 게시글 " + i);

			this.boardRepository.save(board);
			log.info("\t+ board: {}", board);
		} // for
	} // insertDummyDataBeforeAddingPasswordEncoderBean

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(2)
	@DisplayName("insertDummyDataAfterAddingPasswordEncoderBean")
	@Timeout(3L)
	void insertDummyDataAfterAddingPasswordEncoderBean() {
		log.trace("insertDummyDataAfterAddingPasswordEncoderBean() invoked.");

		Member member1 = new Member();
		member1.setId("member");
		member1.setName("회원");
		member1.setPassword(passwordEncoder.encode("member123"));
		member1.setRole(Role.ROLE_MEMBER);
		member1.setEnabled(true);

		this.memberRepository.save(member1);
		log.info("\t+ member1: {}", member1);


		Member member2 = new Member();
		member2.setId("admin");
		member2.setName("관리자");
		member2.setPassword(passwordEncoder.encode("admin123"));
		member2.setRole(Role.ROLE_ADMIN);
		member2.setEnabled(true);

		this.memberRepository.save(member2);
		log.info("\t+ member2: {}", member2);


		for(int i=1; i<=77; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle(member1.getName() + "가 등록한 글 " + i);
			board.setContent(member1.getName() + "가 등록한 게시글 " + i);

			this.boardRepository.save(board);
			log.info("\t+ board: {}", board);
		} // for

		for(int i=1; i<=28; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle(member2.getName() + "가 등록한 글 " + i);
			board.setContent(member2.getName() + "가 등록한 게시글 " + i);

			this.boardRepository.save(board);
			log.info("\t+ board: {}", board);
		} // for
	} // insertDummyDataAfterAddingPasswordEncoderBean

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(3)
	@DisplayName("testFindAll")
	@Timeout(1L)
	void testFindAll() {
		log.trace("testFindAll() invoked.");

		Iterable<Board> boardList = this.boardRepository.findAll();
		boardList.forEach(log::info);
	} // testFindAll

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(4)
	@DisplayName("testGetBoardList")
	@Timeout(1L)
	void testGetBoardList() {
		log.trace("testGetBoardList() invoked.");

		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
		log.info("\t+ pageable: {}", pageable);

		Page<Board> page = this.boardRepository.getBoardList(pageable);

		assertThat(page.getSize()).isGreaterThan(0);
		page.forEach(log::info);
	} // testGetBoardList

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(5)
	@DisplayName("testGetBoardListByMember")
	@Timeout(1L)
	void testGetBoardListByMember() {
		log.trace("testGetBoardListByMember() invoked.");

		Member member = this.memberRepository.findById("admin").orElse(null);

		Objects.requireNonNull(member);
		List<Board> boardList = member.getBoardList();

		boardList.forEach(board -> log.info("\t+ board: {}, member: {}", board, board.getMember().getId()));
	} // testGetBoardListByMember

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(6)
	@DisplayName("testFindById")
	@Timeout(1L)
	void testFindById() {
		log.trace("testFindById() invoked.");

		Board board = this.boardRepository.findById(1L).orElse(null);

		assertThat(board).isNotNull();
		log.info("\t+ board: {}, member: {}", board, board.getMember().getId());
	} // testFindById

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(77)
	@DisplayName("initializeRepositories")
	@Timeout(3L)
	void initializeRepositories() {
		log.trace("initializeRepositories() invoked.");

		this.boardRepository.deleteAll();
		this.memberRepository.deleteAll();
	} // testFindById

} // end class
