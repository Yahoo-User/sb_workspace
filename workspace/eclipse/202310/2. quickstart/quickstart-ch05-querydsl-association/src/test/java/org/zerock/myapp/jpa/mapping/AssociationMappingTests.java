package org.zerock.myapp.jpa.mapping;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// `@Commit` is a test annotation that is used to indicate
// that a test-managed transaction should be committed after the test method has completed.
@Commit
//@Rollback

@AutoConfigureMockMvc
@SpringBootTest
class AssociationMappingTests {
	@Setter(onMethod_ = @Autowired)
	private MockMvc mockMvc;

	@Setter(onMethod_ = @Autowired)
	private MemberRepository memberRepo;
	@Setter(onMethod_ = @Autowired)
	private MyBoardRepository myBoardRepo;


	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("1. prepareTestData")
	@Timeout(5L)
	void prepareTestData() {
		log.trace("prepareTestData() invoked.");

		Member yoseph = new Member();
		yoseph.setId("Yoseph");
		yoseph.setPassword("Yoseph!!");
		yoseph.setName("Yoseph");
		yoseph.setRole("USER");

		this.memberRepo.save(yoseph);

		Member trinity = new Member();
		trinity.setId("Trinity");
		trinity.setPassword("Trinity!!");
		trinity.setName("Trinity");
		trinity.setRole("ADMIN");

		this.memberRepo.save(trinity);

		// ================================

		for(int i=1; i<=3; i++) {
			MyBoard board = new MyBoard();
			board.setTitle("TITLE_" + i);
			board.setContent("CONTENT_" + i);
			board.setCreateDate(new Date());

			board.setMember(yoseph);			// ***

			this.myBoardRepo.save(board);
		} // for

		for(int i=1; i<=3; i++) {
			MyBoard board = new MyBoard();
			board.setTitle("TITLE_" + i);
			board.setContent("CONTENT_" + i);
			board.setCreateDate(new Date());

			board.setMember(trinity);			// ***

			this.myBoardRepo.save(board);
		} // for

	} // prepareTestData


	//	@Disabled
	@Tag("fast")
	@Test
	@Order(2)
	@DisplayName("2. testOneToManyWithSelect")
	@Timeout(5L)
	void testOneToManyWithSelect() {
		log.trace("testOneToManyWithSelect() invoked.");

		final String memberId = "Trinity";

		Member foundMember = this.memberRepo.findById(memberId).orElseThrow();
		log.info("\t+ foundMember: {}", foundMember);

		foundMember.getBoardList().forEach(log::info);
	} // testOneToManyWithSelect


	//	@Disabled
	@Tag("fast")
	@Test
	@Order(3)
	@DisplayName("3. testManyToOneWithSelect")
	@Timeout(5L)
	void testManyToOneWithSelect() {
		log.trace("testManyToOneWithSelect() invoked.");

		final Long seq = 3L;

		MyBoard foundBoard = this.myBoardRepo.findById(seq).orElseThrow();

		log.info("\t+ foundBoard: {}", foundBoard);
		log.info("\t+ writer: {}", foundBoard.getMember());
	} // testManyToOneWithSelect


	//	@Disabled
	@Tag("fast")
	@Test
	@Order(4)
	@DisplayName("4. testOneToManyWithDelete")
	@Timeout(5L)
	void testOneToManyWithDelete() {
		log.trace("testOneToManyWithDelete() invoked.");

		final String memberId = "Trinity";

		Member foundMember = this.memberRepo.findById(memberId).orElseThrow();
		log.info("\t+ foundMember: {}", foundMember);

		this.memberRepo.delete(foundMember);
		log.info("\t+ Deleted.");
	} // testOneToManyWithDelete


//	@Disabled
	@Tag("fast")
	@Test
	@Order(5)
	@DisplayName("5. testManyToOneWithInsert")
	@Timeout(5L)
	void testManyToOneWithInsert() {
		log.trace("testManyToOneWithInsert() invoked.");

		Member yoseph = new Member();
		yoseph.setId("Yoseph");
		yoseph.setPassword("Yoseph!!");
		yoseph.setName("Yoseph");
		yoseph.setRole("USER");

//		this.memberRepo.save(yoseph);

		Member trinity = new Member();
		trinity.setId("Trinity");
		trinity.setPassword("Trinity!!");
		trinity.setName("Trinity");
		trinity.setRole("ADMIN");

//		this.memberRepo.save(trinity);

		// ================================

		for(int i=1; i<=3; i++) {
			MyBoard board = new MyBoard();
			board.setTitle("TITLE_" + i);
			board.setContent("CONTENT_" + i);
			board.setCreateDate(new Date());

			board.setMember(yoseph);			// ***

//			this.myBoardRepo.save(board);
		} // for

		for(int i=1; i<=3; i++) {
			MyBoard board = new MyBoard();
			board.setTitle("TITLE_" + i);
			board.setContent("CONTENT_" + i);
			board.setCreateDate(new Date());

			board.setMember(trinity);			// ***

//			this.myBoardRepo.save(board);
		} // for

		// ================================

		this.memberRepo.save(yoseph);
		this.memberRepo.save(trinity);

		log.info("\t+ Done.");
	} // testManyToOneWithInsert


} // end class
