package org.zerock.myapp.seq_5.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import org.springframework.test.web.servlet.MockMvc;
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
	
//	properties = "spring.jpa.hibernate.ddl-auto=update"
	properties = "spring.jpa.hibernate.ddl-auto=create"
)
public class MemberRepositoryTests {
	@Autowired(required = false)	private MockMvc mockMvc;
	@Autowired(required = true) 	private MemberRepository memberRepo;
	@Autowired(required = true)	private BoardRepositoryWithDynamicQuery boardRepo;
	
	
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
		
		log.info("  + this.mockMvc: {}", this.mockMvc);
		log.info("  + this.memberRepo: {}", this.memberRepo);
		log.info("  + this.boardRepo: {}", this.boardRepo);
	} // postConstruct
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("postConstruct() invoked.");
		
		assertNotNull(this.mockMvc);
		assertNotNull(this.memberRepo);
		assertNotNull(this.boardRepo);
	} // beforeAll
	
	
//	@Disabled
	@Order(1)
//	@Test
	@RepeatedTest(1)
	@DisplayName("prepareData")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void prepareData() {
		log.trace("prepareData() invoked.");
		
		// -----------
		// Step1. Prepare Members (Parents)
		
		final String[] roles = { "user", "admin" };
		
		IntStream.rangeClosed(1, 33).forEachOrdered(seq -> {
			log.trace("forEachOrdered({}) invoked.", seq);
			
			// ---
			Member transientMember = new Member();
			
			transientMember.setId(0L + seq);
			transientMember.setPassword("1234");
			transientMember.setName("Name_" + seq);
			transientMember.setRole(roles[ RandomNumberGenerator.generateInt(0, 2) ]);

			// ---
			try { Thread.sleep(1000L * RandomNumberGenerator.generateInt(1, 3)); }
			catch (InterruptedException _ignored) {}

			// ---
			this.memberRepo.save(transientMember);
		});		// .forEachOrdered

		// -----------
		// Step2. Prepare Boards (Childs)
		
		final String[] writers = { "Yoseph", "Trinity", "Pyramid" };
		
		IntStream.rangeClosed(1, 200).forEachOrdered(seq -> {
			log.trace("forEachOrdered({}) invoked.", seq);

			// ---
			Board transientBoard = new Board();
			
			transientBoard.setSeq(0L + seq);
			transientBoard.setTitle("Title_%03d".formatted(seq));
			transientBoard.setContent("Content_%03d".formatted(seq));
			transientBoard.setWriter(writers[ RandomNumberGenerator.generateInt(0, 3) ]);
			transientBoard.setCnt(seq + RandomNumberGenerator.generateInt(1, 30));
			
			Optional<Member> foundMember = this.memberRepo.findById(0L + RandomNumberGenerator.generateInt(1, 50));
			
			transientBoard.setMember( foundMember.orElse(null) );

			// ---
			this.boardRepo.save(transientBoard);
		});		// .forEachOrdered
	} // prepareData
	
	
	

} // end class
