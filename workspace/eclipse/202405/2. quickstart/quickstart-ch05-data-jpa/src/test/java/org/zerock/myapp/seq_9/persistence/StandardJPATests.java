package org.zerock.myapp.seq_9.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
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
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.seq_6.domain.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

//@EnableJpaAuditing																							// XX
//@DataJpaTest																										// XX

@AutoConfigureMockMvc

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)							// OK, @PersistenceUnit, @PersistenceContext
//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)			// OK, @PersistenceUnit, @PersistenceContext
public class StandardJPATests {
	@Autowired private MockMvc mockMvc;
	
	@PersistenceUnit private EntityManagerFactory emf;					// OK
	@PersistenceContext private EntityManager em;							// OK
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assert this.mockMvc != null;
		log.info("\t1. this.mockMvc: {}", this.mockMvc);
		
		assert this.emf != null;
		log.info("\t2. this.emf: {}", this.emf);
		
		assert this.em != null;
		log.info("\t3. this.em: {}", this.em);
	} // beforeAll
	
//	@Disabled
	@Order(1)
//	@Test
	@RepeatedTest(1)
	@DisplayName("1. testInsertEntity")
	@Timeout(5L)
	@Transactional
	void testInsertEntity() {		// OK, But Not execute INSERT SQL at all.
		log.trace("testInsertEntity() invoked.");
		
		Board transientBoard = new Board();
		
		transientBoard.setTitle("TITLE_"+ 1);
		transientBoard.setWriter("Yoseph");
		transientBoard.setContent("CONTENT_"+1);

		/**
		 * java.lang.IllegalStateException: Not allowed to create transaction on shared EntityManager
		 * 		- use Spring transactions or EJB CMT instead
		 * 
		 * em.getTransaction().begin();					// XX
		 * em.getTransaction().commit();				// XX
		 */
		
		this.em.persist(transientBoard);		// INSERT, But Not SQL executed.
//		this.emf.createEntityManager().persist(transientBoard);			// INSERT, But Not SQL executed.
	} // testInsertEntity

//	@Disabled
	@Order(2)
//	@Test
	@RepeatedTest(1)
	@DisplayName("2. testFindEntity")
	@Timeout(5L)
	void testFindEntity() {	// OK
		log.trace("testFindEntity() invoked.");
		
		Board foundBoard = this.em.<Board>find(Board.class, 1L);
		log.info("\t+ foundBoard: {}", foundBoard);
	} // testFindEntity

//	@Disabled
	@Order(3)
//	@Test
	@RepeatedTest(1)
	@DisplayName("3. testUpdateEntity")
	@Timeout(5L)
	@Transactional
	void testUpdateEntity() {	// OK, But Not execute UPDATE SQL at all.
		log.trace("testUpdateEntity() invoked.");
		
		Board foundBoard = this.em.<Board>find(Board.class, 1L);
		
		assertNotNull(foundBoard);
		foundBoard.setCnt(100);				// UPDATE, But Not SQL executed.
	} // testUpdateEntity

//	@Disabled
	@Order(4)
//	@Test
	@RepeatedTest(1)
	@DisplayName("4. testDeleteEntity")
	@Timeout(5L)
	@Transactional
	void testDeleteEntity() {		// OK, But Not execute DELETE SQL at all.
		log.trace("testDeleteEntity() invoked.");
		
		Board foundBoard = this.em.<Board>find(Board.class, 1L);
		
		assertNotNull(foundBoard);
		this.em.remove(foundBoard);		// DELETE, But Not SQL executed.
	} // testDeleteEntity

	
} // end class
