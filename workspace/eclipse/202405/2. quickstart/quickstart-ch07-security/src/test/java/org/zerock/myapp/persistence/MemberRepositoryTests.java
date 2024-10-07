package org.zerock.myapp.persistence;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.zerock.myapp.common.CommonJUnitTestCallbacks;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.util.RandomNumberGenerator;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@SpringBootTest(
		webEnvironment = WebEnvironment.MOCK, 
//		webEnvironment = WebEnvironment.DEFINED_PORT,
		properties = "spring.jpa.hibernate.ddl-auto=create"
//		properties = "spring.jpa.hibernate.ddl-auto=update"
)
public class MemberRepositoryTests extends CommonJUnitTestCallbacks {
	@Autowired(required = true) private MemberRepository memberRepo;
	@Autowired(required = true) private BoardRepository boardRepo;

	
	@BeforeAll
	public
	void beforeAll() {
		super.beforeAll();
		log.trace("(1) beforeAll() invoked.");
		log.info("  + this.memberRepo: {}", this.memberRepo);
		log.info("  + this.boardRepo: {}", this.boardRepo);
	} // beforeAll
	
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. prepareData")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void prepareData() {
		log.trace("prepareData() invoked.");
		
		// -----------
		// Step1. Prepare Members (Parents)
		// -----------
		final String[] roles = { "user", "admin" };
		final String[] names = { "Yoseph", "Trinity", "Pyramid" };
		
		IntStream.rangeClosed(1, 3).forEachOrdered(seq -> {
			log.trace("forEachOrdered({}) invoked.", seq);
			
			// ---
			Member transientMember = new Member();
			
			transientMember.setId(0L + seq);
			transientMember.setPassword("1234");
			transientMember.setName( names[ RandomNumberGenerator.generateInt(0, 3) ] );
			transientMember.setRole(roles[ RandomNumberGenerator.generateInt(0, 2) ]);

			// ---
			try { Thread.sleep(1000L * RandomNumberGenerator.generateInt(1, 3)); }
			catch (InterruptedException _ignored) {}

			// ---
			this.memberRepo.save(transientMember);
		});		// .forEachOrdered

		// -----------
		// Step2. Prepare Boards (Childs)
		// -----------
		final String[] writers = names;
		
		IntStream.rangeClosed(1, 200).forEachOrdered(seq -> {
			log.trace("forEachOrdered({}) invoked.", seq);

			// ---
			Board transientBoard = new Board();
			
			transientBoard.setSeq(0L + seq);
			transientBoard.setTitle("Title_%03d".formatted(seq));
			transientBoard.setContent("Content_%03d".formatted(seq));
			transientBoard.setWriter(writers[ RandomNumberGenerator.generateInt(0, 3) ]);
			transientBoard.setCnt(seq + RandomNumberGenerator.generateInt(1, 30));

			// ---
			this.boardRepo.save(transientBoard);
		});		// .forEachOrdered
	} // prepareData
	
} // end class


