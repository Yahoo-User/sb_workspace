package org.zerock.myapp.seq_6.association;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
import org.zerock.myapp.seq_4.persistence.MemberRepository;

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
public class ManyToOneUniTests {
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
	@DisplayName("1. testSelect")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testSelect() {
		log.trace("testSelect() invoked.");
		
		// ----------
		Optional<Board> foundBoard = this.boardRepo.findById(33L);
		
		foundBoard.ifPresent(b -> {
			log.info("  + foundBoard: {}", b);
			log.info("\t- member_fk: {}", b.getMember());
		});		// .ifPresent
	} // testSelect
	
	

} // end class
