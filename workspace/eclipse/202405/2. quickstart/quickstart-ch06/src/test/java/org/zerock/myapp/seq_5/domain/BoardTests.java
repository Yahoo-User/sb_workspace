package org.zerock.myapp.seq_5.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.zerock.myapp.seq_0.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_4.domain.Board;

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

@AutoConfigureMockMvc

@SpringBootTest(
		webEnvironment = WebEnvironment.MOCK,
//		webEnvironment = WebEnvironment.DEFINED_PORT,
		
//		properties = "spring.jpa.hibernate.ddl-auto=create"
		properties = "spring.jpa.hibernate.ddl-auto=update"
	)
public class BoardTests extends CommonBeanCallbacks {
	@PersistenceUnit			private EntityManagerFactory emf;
	@PersistenceContext		private EntityManager em;


	@Override
	public void postConstruct() {
		log.trace("postConstruct() invoked.");
		
		log.info("  + this.emf: {}", this.emf);
		log.info("  + this.em: {}", this.em);
	} // postConstruct
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.emf);
		assertNotNull(this.em);
	} // beforeAll
	

	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. testPersistBoard")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testPersistBoard() {
		log.trace("testPersistBoard() invoked.");
		
		Board transientBoard = new Board();
		transientBoard.setTitle("Title");
		transientBoard.setWriter("Yoseph");
		transientBoard.setContent("Content");
		
		EntityManager em = this.emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(transientBoard);										// INSERT
			em.getTransaction().commit();
		} catch(Exception e) {
			em.getTransaction().rollback();
			
			throw e;
		} // try-catch
	} // testPersistBoard
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("2. testLoadBoard")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testLoadBoard() {
		log.trace("testLoadBoard() invoked.");
		
		Board foundBoard = this.em.<Board>find(Board.class, 2L);
		log.info("  + foundBoard: {}", foundBoard);					// SELECT
	} // testLoadBoard
	
//	@Disabled
	@Order(3)
	@Test
//	@RepeatedTest(1)
	@DisplayName("3. testUpdateBoard")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testUpdateBoard() {
		log.trace("testUpdateBoard() invoked.");
		
		EntityManager em = this.emf.createEntityManager();
		
		Board foundBoard = em.<Board>find(Board.class, 2L);
		
		Objects.requireNonNull(foundBoard);
		foundBoard.setCnt(foundBoard.getCnt() + 100);
		
		try {
			em.getTransaction().begin();
			em.merge(foundBoard);												// UPDATE
			em.getTransaction().commit();
		} catch(Exception e) {
			em.getTransaction().rollback();
			throw e;
		} // try-catch
	} // testUpdateBoard
	
//	@Disabled
	@Order(4)
	@Test
//	@RepeatedTest(1)
	@DisplayName("4. testRemoveBoard")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testRemoveBoard() {
		log.trace("testRemoveBoard() invoked.");

		EntityManager em = this.emf.createEntityManager();
		
		Board foundBoard = em.<Board>find(Board.class, 3L);
		Objects.requireNonNull(foundBoard);
		
		try {
			em.getTransaction().begin();
			em.remove(foundBoard);											// DELETE
			em.getTransaction().commit();
		} catch(Exception e) {
			em.getTransaction().rollback();
			
			throw e;
		} // try-catch
	} // testRemoveBoard
	
} // end class
