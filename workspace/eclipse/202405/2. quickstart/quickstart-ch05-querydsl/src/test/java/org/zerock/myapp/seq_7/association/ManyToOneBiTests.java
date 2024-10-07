package org.zerock.myapp.seq_7.association;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
import org.zerock.myapp.seq_1.domain.Board;
import org.zerock.myapp.seq_2.persistence.BoardRepositoryWithDynamicQuery;
import org.zerock.myapp.seq_3.domain.Member;
import org.zerock.myapp.seq_4.persistence.MemberRepository;
import org.zerock.myapp.util.RandomNumberGenerator;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@AutoConfigureMockMvc

@SpringBootTest(
	webEnvironment = WebEnvironment.MOCK, 
//	webEnvironment = WebEnvironment.DEFINED_PORT, 
	
	properties = "spring.jpa.hibernate.ddl-auto=update"
//	properties = "spring.jpa.hibernate.ddl-auto=create"
)
public class ManyToOneBiTests {
	@Autowired(required = true) 	private MemberRepository memberRepo;
	@Autowired(required = true)	private BoardRepositoryWithDynamicQuery boardRepo;
	
	
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
		
		log.info("  + this.memberRepo: {}", this.memberRepo);
		log.info("  + this.boardRepo: {}", this.boardRepo);
	} // postConstruct
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("postConstruct() invoked.");
		
		assertNotNull(this.memberRepo);
		assertNotNull(this.boardRepo);
	} // beforeAll
	
	
//	@Disabled
	@Order(1)
//	@Test
	@RepeatedTest(1)
	@DisplayName("1. testSelectAnMemberWithBidirection")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testSelectAnMemberWithBidirection() {
		log.trace("testSelectAnMemberWithBidirection() invoked.");
		
		// ----------
		Optional<Member> foundMember = this.memberRepo.findById(33L);

		// ----------
		foundMember.ifPresent(m -> {
			log.info("  + foundMember: {}", m);
			m.getBoards().forEach(log::info);
		});		// .ifPresent
	} // testSelectAnMemberWithBidirection
	
	
//	@Disabled
	@Order(2)
//	@Test
	@RepeatedTest(1)
	@DisplayName("2. testInsertWithPersistenceTransition")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testInsertWithPersistenceTransition() {
		log.trace("testInsertWithPersistenceTransition() invoked.");

		// ----------
		String[] roles = { "user", "admin" };
		final List<Member> memberList = new ArrayList<>();
		
		IntStream.rangeClosed(1, 33).forEachOrdered(seq -> {
			log.trace("1. forEachOrdered({}) invoked.", seq);
			
			Member transientMember = new Member();
			
			transientMember.setId(0L + seq);
			transientMember.setPassword("1234");
			transientMember.setName("Name_" + seq);
			transientMember.setRole(roles[ RandomNumberGenerator.generateInt(0, 2) ]);

			memberList.add(transientMember);
		});	 // .forEachOrdered

		// ----------
		memberList.forEach(log::info);
		String[] writers = { "Yoseph", "Trinity", "Pyramid" };
		
		IntStream.rangeClosed(1, 200).forEachOrdered(seq -> {
			log.trace("2. forEachOrdered({}) invoked.", seq);
			
			Board transientBoard = new Board();
			
			transientBoard.setSeq(0L + seq);
			transientBoard.setTitle("Title_" + seq);
			transientBoard.setWriter( writers[ RandomNumberGenerator.generateInt(0, 3) ] );
			transientBoard.setContent("Content_" + seq);
			
			transientBoard.setMember(memberList.get( RandomNumberGenerator.generateInt(0, memberList.size()) ));
			this.boardRepo.save(transientBoard);							// OK, Persistence Transition Effect
			
			log.info("  + transientBoard: {}", transientBoard);
		});		// .forEachOrdered

		// ----------
		memberList.forEach(transientMember -> {
			log.trace("3. forEach({}) invoked.", transientMember);
			transientMember.getBoards().forEach(log::info);

//			this.memberRepo.save(transientMember);						// XX, Persistence Transition Effect
		});		// .forEach
	}	// testInsertWithPersistenceTransition
	
	
//	@Disabled
	@Order(3)
//	@Test
	@RepeatedTest(1)
	@DisplayName("3. testDeleteByCascade")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testDeleteByCascadeWithPersistenceTransition() {
		log.trace("testDeleteByCascadeWithPersistenceTransition() invoked.");
		
		// ----------
		this.memberRepo.deleteById(32L);
	}	// testDeleteByCascadeWithPersistenceTransition
	
	
	
	
} // end class
