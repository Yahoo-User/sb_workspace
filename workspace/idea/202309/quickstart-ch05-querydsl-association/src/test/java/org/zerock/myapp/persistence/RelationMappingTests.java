package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.Member;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
public class RelationMappingTests {
	@Autowired private MockMvc mockMvc;
	@Autowired private BoardRepository boardRepository;
	@Autowired private MemberRepository memberRepository;
	
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assert this.mockMvc != null;
		log.info("\t1. this.mockMvc: {}", this.mockMvc);
		
		Objects.requireNonNull(this.boardRepository);
		log.info("\t2. this.boardRepository: {}", this.boardRepository);
		
		assertNotNull(this.memberRepository);
		log.info("\t3. this.memberRepository: {}", this.memberRepository);
	} // beforeAll
	
	
//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("testManyToOneDataInserts")
	@Timeout(1L)
	void testManyToOneDataInserts() {
		log.trace("testManyToOneDataInserts() invoked.");
		
		Member member1 = new Member();
		
		member1.setId("member1");
		member1.setPassword("member11");
		member1.setName("Yoseph");
		member1.setRole("User");
		
		this.memberRepository.save(member1);
		
//		----------------------------
		
		Member member2 = new Member();
		
		member2.setId("member2");
		member2.setPassword("member21");
		member2.setName("Trinity");
		member2.setRole("Admin");
		
		this.memberRepository.save(member2);
		
//		----------------------------
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();

			board.setMember(member1);
			
			board.setTitle("TITLE_" + i);
			board.setContent("CONTENT_" + i);
			
			this.boardRepository.save(board);
		} // for
		
//		----------------------------
		
		for(int i=1; i<=5; i++) {
			Board board = new Board();

			board.setMember(member2);

			board.setTitle("TITLE_" + i);
			board.setContent("CONTENT_" + i);
			
			this.boardRepository.save(board);
		} // for
		
	} // testManyToOneDataInserts
	
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testManyToOneSelect")
	@Timeout(1L)
	void testManyToOneSelect() {
		log.trace("testManyToOneSelect() invoked.");
		
		Board board = this.boardRepository.findById(3L).get();
		
		assertNotNull(board);
		log.info("\t+ board: {}", board);
//		log.info("\t+ member: {}", board.getMember());
	} // testManyToOneSelect
	
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("testOneToManySelect")
	@Timeout(1L)
	void testOneToManySelect() {
		log.trace("testOneToManySelect() invoked.");
		
		Member member1 = this.memberRepository.findById("member1").get();
		
		Objects.requireNonNull(member1);
		log.info("\t+ member1: {}", member1);
		
//		member1.getBoardList().forEach(log::info);
	} // testOneToManySelect
	
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("testManyToOneInsertByPersistenceTransition")
	@Timeout(1L)
	void testManyToOneInsertByPersistenceTransition() {
		log.trace("testManyToOneInsertByPersistenceTransition() invoked.");
		
		Member member1 = new Member();
		
		member1.setId("member1");
		member1.setPassword("member11");
		member1.setName("Yoseph");
		member1.setRole("User");
		
//		this.memberRepository.save(member1);
		
//		----------------------------
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();

//			board.setMember(member1);
			
			board.setTitle("TITLE_"+i);
			board.setContent("CONTENT_"+i);
			
//			this.boardRepository.save(board);
		} // for
		
		this.memberRepository.save(member1);
		
//		----------------------------
		
		Member member2 = new Member();
		
		member2.setId("member2");
		member2.setPassword("member21");
		member2.setName("Trinity");
		member2.setRole("Admin");
		
//		this.memberRepository.save(member2);
		
//		----------------------------
		
		for(int i=1; i<=5; i++) {
			Board board = new Board();

//			board.setMember(member2);
			
			board.setTitle("TITLE_"+i);
			board.setContent("CONTENT_"+i);
			
//			this.boardRepository.save(board);
		} // for
		
		this.memberRepository.save(member2);
	} // testManyToOneInsertByPersistenceTransition
	
	
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("testOneToManyDeleteByPersistenceTransition")
	@Timeout(1L)
	void testOneToManyDeleteByPersistenceTransition() {
		log.trace("testOneToManyDeleteByPersistenceTransition() invoked.");
		
		this.memberRepository.deleteById("member2");
	} // testOneToManyDeleteByPersistenceTransition

} // end class
