package org.zerock.myapp;

import java.util.Date;
import java.util.Objects;
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
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.persistence.BoardDao;
import org.zerock.myapp.persistence.MemberDao;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@SpringBootTest
class DataInitializerTests {
	
	@Setter(onMethod_= @Autowired)
	private BoardDao boardDao;
	
	@Autowired
	private MemberDao memberDao;
	
	
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
		
		Objects.requireNonNull(this.boardDao);
		log.info("\t+ boardDao: {}, type: {}", this.boardDao, this.boardDao.getClass().getName());
		
		Objects.requireNonNull(this.memberDao);
		log.info("\t+ memberDao: {}, type: {}", this.memberDao, this.memberDao.getClass().getName());
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.debug("afterEach() invoked.");
		
	} // afterEach
	
//	============================

	@Test
	@Order(1)
	@DisplayName("initialDataInsert")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void initialDataInsert() {
		log.debug("initialDataInsert() invoked.");
		
		Member member1 = new Member();
		member1.setId("user1");
		member1.setPw("pass1");
		member1.setName("name1");
		member1.setRole("role1");
		
		this.memberDao.save(member1);
		
		Member member2 = new Member();
		member2.setId("user2");
		member2.setPw("pass2");
		member2.setName("name2");
		member2.setRole("role2");
		
		this.memberDao.save(member2);
		
//		-------------------------
		
		for(int i=1;i<=3;i++) {
			Board board = new Board();
			board.setTitle("TITLE_"+i);
			board.setWriter("user1");
			board.setContent("CONTENT_"+i);
			board.setCreateDate(new Date());
			board.setCnt(0l);
			
			this.boardDao.save(board);
		} // for
		
		for(int i=4;i<=10;i++) {
			Board board = new Board();
			board.setTitle("TITLE_"+i);
			board.setWriter("user2");
			board.setContent("CONTENT_"+i);
			board.setCreateDate(new Date());
			board.setCnt(0l);
			
			this.boardDao.save(board);
		} // for
	} // initialDataInsert

} // end class
