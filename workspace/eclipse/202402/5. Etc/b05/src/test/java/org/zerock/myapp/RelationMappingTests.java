package org.zerock.myapp;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.myapp.dao.BoardRepository;
import org.zerock.myapp.dao.MemberRepository;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.Member;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@SpringBootTest
//@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class RelationMappingTests {
	
	@Setter(onMethod_= {@Autowired})
	private MemberRepository memberDao;
	
	@Setter(onMethod_= {@Autowired})
	private BoardRepository boardDao;
	
	
	
	@BeforeAll
	static void beforeAll() {
		log.debug("beforeAll() invoked.");
		
	} // beforeAll
	
	@AfterAll
	static void afterAll() {
		log.debug("afterAll() invoked.");
		
	} // afterAll
	
	@BeforeEach
	void beforeEach() {
		log.debug("beforeEach() invoked.");
		
		assert this.boardDao != null;
		log.info("\t+ boardDao: {}, type: {}", this.boardDao, this.boardDao.getClass().getName());
		
		Objects.requireNonNull(this.memberDao);
		log.info("\t+ memberDao: {}, type: {}", this.memberDao, this.memberDao.getClass().getName());
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.debug("afterEach() invoked.");
		
	} // afterEach
	
//	-------------------------------- //
	
	@Test
	@Order(1)
	@DisplayName("testInsertOnManyToOne")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testInsertOnManyToOne() {
		log.debug("testInsertOnManyToOne() invoked.");
		
		Member member1 = new Member();
		member1.setId("member1");
		member1.setPassword("password1");
		member1.setName("fullname1");
		member1.setRole("User");
		
//		this.memberDao.save(member1);	// Comment for `persistence transition`
		
//		----------------------
		
		Member member2 = new Member();
		member2.setId("member2");
		member2.setPassword("password2");
		member2.setName("fullname2");
		member2.setRole("Admin");
		
//		this.memberDao.save(member2);	// Comment for `persistence transition`
		
//		----------------------
		
		for(int i=1;i<=7;i++) {
			Board board = new Board();
			
			board.setTitle("TITLE_"+i);
			board.setContent("CONTENT_"+i);
			board.setWriter(member1);
			board.setCreateDate(new Date());
			board.setCnt(0l);
			
//			this.boardDao.save(board);	// Comment for `persistence transition`
		} // for
		
//		----------------------
		
		for(int i=1;i<=3;i++) {
			Board board = new Board();
			
			board.setTitle("ADMIN_TITLE_"+i);
			board.setContent("ADMIN_CONTENT_"+i);
			board.setWriter(member2);
			board.setCreateDate(new Date());
			board.setCnt(0l);
			
//			this.boardDao.save(board);	// Comment for `persistence transition`
		} // for
		
//		----------------------
		
		// For `persistence transition` (***)
		// 영속성 전이의 단점: `1:N`관계에서, `N`의 키를 영속성 저장 이후에도 알 수 없다!
		// 영속성 전이를 안쓰는 경우: 영속성 전이의 단점 극복 
		// 따라서, 영속성 전이를 사용하는 것이 좋지 않음 (책의 내용과 반대)
		this.memberDao.save(member1);
		this.memberDao.save(member2);
		
//		----------------------
		
		// Check whether `persistence transition` works.
		log.info("************************************************");
		log.info("* Check whether `persistence transition` works *");
		log.info("************************************************");
		
		log.info("member1: {}", member1);
		member1.getBoardList().forEach(log::info);
		
		log.info("--------------------------");
		
		log.info("member2: {}", member2);
		member2.getBoardList().forEach(log::info);
	} // testInsertOnManyToOne
	
	
	@Test
	@Order(2)
	@DisplayName("testManyToOneWhenDeletingOne")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testManyToOneWhenDeletingOne() {
		log.debug("testManyToOneWhenDeletingOne() invoked.");
		
		Optional<Member> optional = this.memberDao.findById("member2");
		
		optional.ifPresentOrElse(m -> {
			log.info("\t+ member to delete: {}", m);
			
			m.getBoardList().forEach(log::info);
			
			this.memberDao.delete(m);					// XX: When deleting one, error raised (FK integrity violation)
		}, () -> {
			log.info("\t+ No member found.");
		}); // ifPresent
	} // testManyToOneWhenDeletingOne
	
	
	@Test
	@Order(3)
	@DisplayName("testManyToOneWhenDeletingMany")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testManyToOneWhenDeletingMany() {
		log.debug("testManyToOneWhenDeletingMany() invoked.");
		
		Optional<Board> optional = this.boardDao.findById(4l);
//		optional.get().getWriter().getBoardList().forEach(log::info);	// XX if `Parent` entity `FetchType.LAZY`
		
//		----------------
		
//		this.boardDao.delete(optional.get());
//		this.boardDao.deleteById(optional.get().getSeq());
		
//		----------------
		
		optional.ifPresentOrElse(b -> {
			log.info("\t+ board to delete: {}", b);
			
			this.boardDao.delete(b);					// OK: When deleting many
		}, () -> {
			log.info("\t+ No board found.");
		}); // ifPresent
		
//		----------------
		
	} // testManyToOneWhenDeletingMany
	
	
	@Test
	@Order(4)
	@DisplayName("testManyToOneSelect")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testManyToOneSelect() {
		log.debug("testManyToOneSelect() invoked.");
		
		Board board = this.boardDao.findById(6l).get();
		log.info("\t+ board: {}", board);
		
		Member writer = board.getWriter();
		log.info("\t+ writer: {}", writer);
		
		writer.getBoardList().forEach(log::info);
	} // testManyToOneSelect
	
	
	@Test
	@Order(5)
	@DisplayName("testOneToManySelect")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void testOneToManySelect() {
		log.debug("testOneToManySelect() invoked.");
		
		Optional<Member> optional = this.memberDao.findById("member1");
		optional.ifPresentOrElse(m -> {
			log.info("\t+ found member: {}", m);
			
			m.getBoardList().forEach(log::info);
		}, () -> {
			log.info("\t+ No member found.");
		}); // ifPresent
	} // testOneToManySelect
	

} // end class
