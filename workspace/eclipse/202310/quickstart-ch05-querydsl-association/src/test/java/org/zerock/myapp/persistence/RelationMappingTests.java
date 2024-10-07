package org.zerock.myapp.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Objects;

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
import org.zerock.myapp.domain.Member;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//`@Commit` is a test annotation that is used to indicate
//that a test-managed transaction should be committed after the test method has completed.
@Commit

@SpringBootTest		// With mocking embedded WAS.
//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)	// With embedded WAS.

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RelationMappingTests {
	@Autowired private BoardRepository boardRepo;
	@Autowired private MemberRepository memberRepo;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		Objects.requireNonNull(this.boardRepo);
		log.info("\t+ this.boardRepo: {}", this.boardRepo);
		
		assertNotNull(this.memberRepo);
		log.info("\t+ this.memberRepo: {}", this.memberRepo);
	} // beforeAll
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testManyToOneDataInserts")
	@Timeout(10L)
	void testManyToOneDataInserts() {
		log.trace("testManyToOneDataInserts() invoked.");
		
		Member member1 = new Member();
		
		member1.setId("member1");
		member1.setPassword("member11");
		member1.setName("Yoseph");
		member1.setRole("User");
		
		this.memberRepo.save(member1);
		
//		----------------------------
		
		Member member2 = new Member();
		
		member2.setId("member2");
		member2.setPassword("member21");
		member2.setName("Trinity");
		member2.setRole("Admin");
		
		this.memberRepo.save(member2);
		
//		----------------------------
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			
			board.setTitle("TITLE_"+i);
			board.setContent("CONTENT_"+i);			
			board.setMember(member1);
			
			this.boardRepo.save(board);
		} // for
		
//		----------------------------
		
		for(int i=1; i<=5; i++) {
			Board board = new Board();
			
			board.setTitle("TITLE_"+i);
			board.setContent("CONTENT_"+i);			
			board.setMember(member2);
			
			this.boardRepo.save(board);
		} // for
		
	} // testManyToOneDataInserts
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testManyToOneSelect")
	@Timeout(1L)
	void testManyToOneSelect() {
		log.trace("testManyToOneSelect() invoked.");
		
		Board board = this.boardRepo.findById(3L).orElseThrow();
		
		assertNotNull(board);
		log.info("\t+ board: {}", board);
		log.info("\t+ member: {}", board.getMember());
	} // testManyToOneSelect
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("testOneToManySelect")
	@Timeout(1L)
	void testOneToManySelect() {
		log.trace("testOneToManySelect() invoked.");
		
		Member member1 = this.memberRepo.findById("member1").orElseThrow();
		
		Objects.requireNonNull(member1);
		log.info("\t+ member1: {}", member1);
		
		member1.getBoardList().forEach(log::info);
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
		
//		this.memberRepo.save(member1);
		
//		----------------------------
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			
			board.setTitle("TITLE_"+i);
			board.setContent("CONTENT_"+i);	
			
			board.setMember(member1);
			
//			this.boardRepo.save(board);
		} // for
		
		this.memberRepo.save(member1);
		
//		----------------------------
		
		Member member2 = new Member();
		
		member2.setId("member2");
		member2.setPassword("member21");
		member2.setName("Trinity");
		member2.setRole("Admin");
		
//		this.memberRepo.save(member2);
		
//		----------------------------
		
		for(int i=1; i<=5; i++) {
			Board board = new Board();
			
			board.setTitle("TITLE_"+i);
			board.setContent("CONTENT_"+i);	
			
			board.setMember(member2);
			
//			this.boardRepo.save(board);
		} // for
		
		this.memberRepo.save(member2);
		
	} // testManyToOneInsertByPersistenceTransition
	
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("testOneToManyDeleteByPersistenceTransition")
	@Timeout(1L)
	void testOneToManyDeleteByPersistenceTransition() {
		log.trace("testOneToManyDeleteByPersistenceTransition() invoked.");
		
		this.memberRepo.deleteById("member2");
	} // testOneToManyDeleteByPersistenceTransition

} // end class
