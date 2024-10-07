package org.zerock.myapp.seq_8.service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import javax.sql.DataSource;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.myapp.seq_0.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_4.domain.Board;
import org.zerock.myapp.seq_6.persistence.BoardRepository;
import org.zerock.myapp.seq_7.service.BoardService;
import org.zerock.myapp.util.RandomNumberGenerator;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc

@SpringBootTest(
	webEnvironment = WebEnvironment.MOCK,
//	webEnvironment = WebEnvironment.DEFINED_PORT,
	
//	properties = "spring.jpa.hibernate.ddl-auto=create"
	properties = "spring.jpa.hibernate.ddl-auto=update"
)
public class BoardServiceTests extends CommonBeanCallbacks {
	@Autowired(required = false)	private MockMvc mockMvc;
	@Autowired(required = false)	private WebApplicationContext beansContainer;
	
	// Dependency Injection By The Specified Bean Name.
	@Autowired(required = false) @Qualifier("dataSource")
	private DataSource dataSource;

	// Dependency Injection By The Specified Bean Type.
	@Autowired private BoardService boardService;
	@Autowired private BoardRepository boardRepo;

	private String[] writers = { "Yoseph", "Trinity", "Pyramid" };
	
	
	@Override
	public void postConstruct() {
		log.trace("postConstruct() invoked.");

		log.info("  + this.mockMvc: {}", this.mockMvc);
		log.info("  + this.beansContainer: {}", this.beansContainer);
		log.info("  + this.dataSource: {}", this.dataSource);
		log.info("  + this.boardService: {}", this.boardService);
		log.info("  + this.boardRepo: {}", this.boardRepo);
	} 	// postConstruct
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		log.info("  + beanDefinitionCount: {}", this.beansContainer.getBeanDefinitionCount());

		/*
		String[] beanDefinitionNames = this.beansContainer.getBeanDefinitionNames();
		Stream.<String>of(beanDefinitionNames).forEachOrdered(n -> {
			log.info("  + beanName( {} )", n);
			log.info("    type: {}", this.beansContainer.getBean(n).getClass().getName());
		});		// .forEachOrdered
		*/

		assert this.boardRepo != null;
		assert this.boardService != null;
	} 	// beforeAll
	
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. prepareData()")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void prepareData() {
		log.trace("prepareData() invoked.");
		
		// -------
		// 1. CREATE
		// -------		
		IntStream.rangeClosed(1, 200).forEachOrdered(seq -> {
			log.trace("forEachOrdered({}) invoked.", seq);
			
			Board transientBoard = new Board();
			
			transientBoard.setTitle("Title_%03d".formatted(seq));
			transientBoard.setWriter( this.writers[ RandomNumberGenerator.generateInt(0, 3) ] );
			transientBoard.setContent("Content_%03d".formatted(seq));
			
			this.boardRepo.save(transientBoard);
		});		// .forEach

		// -------
		// 2. UPDATE
		// -------
		IntStream.rangeClosed(1, 77).forEachOrdered(seq -> {
			log.trace("forEachOrdered({}) invoked.", seq);

			int cnt = seq;
			int randomSEQ = RandomNumberGenerator.generateInt(1, 200);
			
			Optional<Board> foundBoard = this.boardRepo.findById(0L + randomSEQ);
			foundBoard.ifPresent(b -> {
				b.setCnt(cnt);
				
				this.boardRepo.save(b);
			});	// .ifPresent
		});		// .forEachOrdered
	} 	// prepareData
	
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("2. testFindAllBoards")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testFindAllBoards() {
		log.trace("testFindAllBoards() invoked.");

		// -------
//		List<Board> foundAllBoards = this.boardService.findAllBoards();			// 1
		
		// -------
		int currPage = 1;
		Page<Board> foundAllBoards = this.boardService.findAllBoards(currPage);		// 2
		
		foundAllBoards.forEach(log::info);
	} 	// testFindAllBoards
	
//	@Disabled
	@Order(3)
	@Test
//	@RepeatedTest(1)
	@DisplayName("3. testCreateBoard")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testCreateBoard() {
		log.trace("testCreateBoard() invoked.");

		// -------
		Board transientBoard = new Board();
		
		transientBoard.setTitle("NewTitle");
		transientBoard.setWriter( this.writers[ RandomNumberGenerator.generateInt(0, 3) ] );
		transientBoard.setContent("NewContent");
		transientBoard.setCnt( RandomNumberGenerator.generateInt(1, 77) );
		
		log.info("  + transientBoard: {}", transientBoard);
		
		boolean isCreated = this.boardService.createBoard(transientBoard);
		log.info("  + Done({}).", isCreated);
	}	// testCreateBoard
	
//	@Disabled
	@Order(4)
	@Test
//	@RepeatedTest(1)
	@DisplayName("4. testFindBoardById")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testFindBoardById() {
		log.trace("testFindBoardById() invoked.");

		// -------
		int randomSEQ = RandomNumberGenerator.generateInt(1, 201);
		Board foundBoard = this.boardService.findBoardById(0L + randomSEQ);
		
		log.info("  + foundBoard: {}", foundBoard);
	} 	// testFindBoardById
	
//	@Disabled
	@Order(5)
	@Test
//	@RepeatedTest(1)
	@DisplayName("5. testModifyBoard")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testModifyBoard() {
		log.trace("testModifyBoard() invoked.");

		// -------
		int randomSEQ = RandomNumberGenerator.generateInt(1, 201);
		Optional<Board> foundBoard = this.boardRepo.findById(0L + randomSEQ);
		
		foundBoard.ifPresent(b -> {
			b.setTitle("Modified_%03d".formatted(randomSEQ));
			b.setContent("Modified.");
			
			boolean isModified = this.boardService.modifyBoard(b);
			log.info("  + Done({}).", isModified);
		});	// .ifPresent
	} 	// testModifyBoard
	
//	@Disabled
	@Order(6)
	@Test
//	@RepeatedTest(1)
	@DisplayName("6. testRemoveBoard")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testRemoveBoard() {
		log.trace("testRemoveBoard() invoked.");

		// -------
		int randomSEQ = RandomNumberGenerator.generateInt(1, 201);
		this.boardService.removeBoard(0L + randomSEQ);
		
		log.info("  + Done.");
	} 	// testRemoveBoard

} // end class


