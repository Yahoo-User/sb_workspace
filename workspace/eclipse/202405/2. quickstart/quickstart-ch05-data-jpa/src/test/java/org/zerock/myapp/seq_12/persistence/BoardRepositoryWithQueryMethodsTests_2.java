package org.zerock.myapp.seq_12.persistence;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Slice;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.seq_11.persistence.BoardRepositoryWithQueryMethods;
import org.zerock.myapp.seq_6.domain.Board;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc

@SpringBootTest(
//		webEnvironment = WebEnvironment.MOCK, 
	webEnvironment = WebEnvironment.DEFINED_PORT, 
	
//	properties = "spring.jpa.hibernate.ddl-auto=create"
	properties = "spring.jpa.hibernate.ddl-auto=update"
)
public class BoardRepositoryWithQueryMethodsTests_2 {
	@Setter(onMethod_ = @Autowired(required = false)) private MockMvc mockMvc;
	@Setter(onMethod_ = @Autowired(required = true))  private BoardRepositoryWithQueryMethods boardRepo;
		
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assert this.mockMvc != null; 
		assert this.boardRepo != null; 
	} // beforeAll
	
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("0. prepareTestData")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void prepareTestData() {	
		log.trace("prepareTestData() invoked.");

		// ---------
		this.boardRepo.deleteAllInBatch();

		// ---------
		IntStream.rangeClosed(1, 200).forEachOrdered(seq -> {
			log.trace("  forEachOrdered(%03d) invoked.".formatted(seq));

			Board transientBoard = new Board();
			
			transientBoard.setTitle("Title_"+seq);
			transientBoard.setWriter("Yoseph");
			transientBoard.setContent("Content_"+seq);

			this.boardRepo.save(transientBoard);
		});		// .forEachOrdered
	} // prepareTestData
	
//	@Disabled
	@Order(2)
//	@Test
	@RepeatedTest(1)
	@DisplayName("1. testFindBySeq")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindBySeq() {
		log.trace("testFindBySeq() invoked.");

		// ---------
		int randomSeq = (int) (Math.random() * (200 - 1)) + 1;
		Long searchSeq = 0L + randomSeq;
		
		Slice<Board> foundSlice = this.boardRepo.findBySeq(searchSeq);
		Objects.requireNonNull(foundSlice);
		
		// ---------
		log.info("  + hasContent({}), isEmpty({}), isFirst({}), isLast({})",
							foundSlice.hasContent(),
							foundSlice.isEmpty(),
							foundSlice.isFirst(),
							foundSlice.isLast());
		
		log.info("  + hasNext({}), hasPrevious({}), getSize({}), getNumber({}), getNumberOfElements({})",
							foundSlice.hasNext(),
							foundSlice.hasPrevious(),
							foundSlice.getSize(),
							foundSlice.getNumber(),
							foundSlice.getNumberOfElements());
		
		log.info("  + getSort({})", foundSlice.getSort());

		// ---------
		foundSlice.forEach(b -> {
			log.trace("forEach({}) invoked.", b);
			
			b.setCnt(randomSeq);
			this.boardRepo.save(b);
		});		// .forEach
	} // testFindBySeq
	
//	@Disabled
	@Order(3)
//	@Test
	@RepeatedTest(1)
	@DisplayName("2. testFindByTitle")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByTitle() {
		log.trace("testFindByTitle() invoked.");

		// ---------
		int randomSeq = (int) (Math.random() * (200 - 1)) + 1;
		String searchTitle = "Title_" + randomSeq;
		
		Slice<Board> foundSlice = this.boardRepo.findByTitle(searchTitle);
		Objects.requireNonNull(foundSlice);
		
		// ---------
		log.info("  + hasContent({}), isEmpty({}), isFirst({}), isLast({})",
							foundSlice.hasContent(),
							foundSlice.isEmpty(),
							foundSlice.isFirst(),
							foundSlice.isLast());
		
		log.info("  + hasNext({}), hasPrevious({}), getSize({}), getNumber({}), getNumberOfElements({})",
							foundSlice.hasNext(),
							foundSlice.hasPrevious(),
							foundSlice.getSize(),
							foundSlice.getNumber(),
							foundSlice.getNumberOfElements());
		
		log.info("  + getSort({})", foundSlice.getSort());

		// ---------
		foundSlice.forEach(b -> {
			log.trace("forEach({}) invoked.", b);

			b.setCnt(randomSeq);
			this.boardRepo.save(b);
		});		// .forEach
	} // testFindByTitle
	
//	@Disabled
	@Order(4)
//	@Test
	@RepeatedTest(1)
	@DisplayName("3. testFindByWriter")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByWriter() {
		log.trace("testFindByWriter() invoked.");

		// ---------
		String searchWriter = "Yoseph";
		
		Slice<Board> foundSlice = this.boardRepo.findByWriter(searchWriter);
		Objects.requireNonNull(foundSlice);
		
		// ---------
		log.info("  + hasContent({}), isEmpty({}), isFirst({}), isLast({})",
							foundSlice.hasContent(),
							foundSlice.isEmpty(),
							foundSlice.isFirst(),
							foundSlice.isLast());
		
		log.info("  + hasNext({}), hasPrevious({}), getSize({}), getNumber({}), getNumberOfElements({})",
							foundSlice.hasNext(),
							foundSlice.hasPrevious(),
							foundSlice.getSize(),
							foundSlice.getNumber(),
							foundSlice.getNumberOfElements());
		
		log.info("  + getSort({})", foundSlice.getSort());

		// ---------
		foundSlice.forEach(b -> {
			log.trace("forEach({}) invoked.", b);

			int randomSeq = (int) (Math.random() * (200 - 1)) + 1;
			b.setCnt(randomSeq);
			
			this.boardRepo.save(b);
		});		// .forEach
	} // testFindByWriter
	
//	@Disabled
	@Order(5)
//	@Test
	@RepeatedTest(1)
	@DisplayName("4. testFindByContent")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByContent() {
		log.trace("testFindByContent() invoked.");

		// ---------
		int randomSeq = (int) (Math.random() * (200 - 1)) + 1;
		String searchContent = "Content_" + randomSeq;
		
		Slice<Board> foundSlice = this.boardRepo.findByContent(searchContent);
		Objects.requireNonNull(foundSlice);
		
		// ---------
		log.info("  + hasContent({}), isEmpty({}), isFirst({}), isLast({})",
							foundSlice.hasContent(),
							foundSlice.isEmpty(),
							foundSlice.isFirst(),
							foundSlice.isLast());
		
		log.info("  + hasNext({}), hasPrevious({}), getSize({}), getNumber({}), getNumberOfElements({})",
							foundSlice.hasNext(),
							foundSlice.hasPrevious(),
							foundSlice.getSize(),
							foundSlice.getNumber(),
							foundSlice.getNumberOfElements());
		
		log.info("  + getSort({})", foundSlice.getSort());

		// ---------
		foundSlice.forEach(b -> {
			log.trace("forEach({}) invoked.", b);

			b.setContent("Modified " + searchContent);
			this.boardRepo.save(b);
		});		// .forEach
	} // testFindByContent
	
//	@Disabled
	@Order(6)
//	@Test
	@RepeatedTest(1)
	@DisplayName("5. testFindByCnt")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByCnt() {
		log.trace("testFindByCnt() invoked.");

		// ---------
		int randomSeq = (int) (Math.random() * (200 - 1)) + 1;
		int searchCnt = randomSeq;
		
		Slice<Board> foundSlice = this.boardRepo.findByCnt(searchCnt);
		Objects.requireNonNull(foundSlice);
		
		// ---------
		log.info("  + hasContent({}), isEmpty({}), isFirst({}), isLast({})",
							foundSlice.hasContent(),
							foundSlice.isEmpty(),
							foundSlice.isFirst(),
							foundSlice.isLast());
		
		log.info("  + hasNext({}), hasPrevious({}), getSize({}), getNumber({}), getNumberOfElements({})",
							foundSlice.hasNext(),
							foundSlice.hasPrevious(),
							foundSlice.getSize(),
							foundSlice.getNumber(),
							foundSlice.getNumberOfElements());
		
		log.info("  + getSort({})", foundSlice.getSort());

		// ---------
		foundSlice.forEach(b -> {
			log.trace("forEach({}) invoked.", b);
			
			b.setTitle("Modified Title_" + randomSeq);
			this.boardRepo.save(b);
		});		// .forEach
	} // testFindByCnt
	
	

	
} // end class
