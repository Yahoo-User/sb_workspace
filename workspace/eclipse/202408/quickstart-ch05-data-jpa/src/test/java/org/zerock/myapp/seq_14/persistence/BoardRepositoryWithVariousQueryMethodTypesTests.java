package org.zerock.myapp.seq_14.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.seq_13.persistence.BoardRepositoryWithVariousQueryMethodTypes;
import org.zerock.myapp.seq_6.domain.Board;

import lombok.Cleanup;
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
public class BoardRepositoryWithVariousQueryMethodTypesTests {
	@Autowired(required = false) private MockMvc mockMvc;
	@Autowired(required = true)  private BoardRepositoryWithVariousQueryMethodTypes boardRepo;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		// -----------
		assertNotNull(this.mockMvc);
		assertNotNull(this.boardRepo);
	} // beforeAll
	
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. prepareData")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void prepareData() {
		log.trace("prepareData() invoked.");

		// ---------
		// No needed. automatically all deleted when creating a table.
//		this.boardRepo.deleteAllInBatch();

		// ---------
		IntStream.rangeClosed(1, 200).forEachOrdered(seq -> {
			log.trace("  forEachOrdered(%03d) invoked.".formatted(seq));

			// ---------
			long max = 5L, min = 0L;
			long randomSleepTime = (int) (Math.random() * (max - min)) + min;
			log.info("  randomSleepTime: {} seconds", randomSleepTime);
			
			try { Thread.sleep(1000 * randomSleepTime); }
			catch (InterruptedException _ignored) {}

			// ---------
			Board transientBoard = new Board();
			
			transientBoard.setTitle("Title_"+seq);
			transientBoard.setWriter("Yoseph");
			transientBoard.setContent("Content_"+seq);

			// ---------
			this.boardRepo.save(transientBoard);
		});		// .forEachOrdered
	} // prepareData
	
//	@Disabled
	@Order(2)
//	@Test
	@RepeatedTest(1)
	@DisplayName("2. testFindByContentContaining")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByContentContaining() {
		log.trace("testFindByContentContaining() invoked.");

		// ---------
		String searchContent = "tent_7";
		
		@Cleanup("clear")
		List<Board> foundList = this.boardRepo.findByContentContaining(searchContent);
		Objects.requireNonNull(foundList);

		// ---------
		foundList.forEach(log::info);
	} // testFindByContentContaining
	
//	@Disabled
	@Order(3)
//	@Test
	@RepeatedTest(1)
	@DisplayName("3. testFindByTitleContainingOrContentContaining")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByTitleContainingOrContentContaining() {
		log.trace("testFindByTitleContainingOrContentContaining() invoked.");

		// ---------
		String searchTitle = "tle_3", searchContent = "tent_7";
		Slice<Board> foundSlice = this.boardRepo.findByTitleContainingOrContentContaining(searchTitle, searchContent);
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
		foundSlice.forEach(log::info);
		
		// ---------
		foundSlice.getContent().clear();
	} // testFindByTitleContainingOrContentContaining
	
//	@Disabled
	@Order(4)
//	@Test
	@RepeatedTest(1)
	@DisplayName("4. testFindByTitleContainingOrderBySeqDesc")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByTitleContainingOrderBySeqDesc() {
		log.trace("testFindByTitleContainingOrderBySeqDesc() invoked.");

		// ---------
		String searchTitle = "tle_1";
		Slice<Board> foundSlice = this.boardRepo.findByTitleContainingOrderBySeqDesc(searchTitle);
		assertNotNull(foundSlice);
		
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
		foundSlice.forEach(log::info);
		
		// ---------
		foundSlice.getContent().clear();
	}	// testFindByTitleContainingOrderBySeqDesc
	
//	@Disabled
	@Order(5)
//	@Test
	@RepeatedTest(1)
	@DisplayName("5-1. testFindByContentContainingOrderBySeqDesc1")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByContentContainingOrderBySeqDesc1() {
		log.trace("testFindByContentContainingOrderBySeqDesc1() invoked.");

		// ---------
		String searchContent = "tent_1";
		
		int currPage = 0, linePerPage = 10;
		Pageable paging = PageRequest.of(currPage, linePerPage);
		
		Page<Board> foundPage = this.boardRepo.findByContentContainingOrderBySeqDesc(searchContent, paging);
		Objects.requireNonNull(foundPage);

		// ---------
		log.info("  1. isEmpty({}), isFirst({}), isLast({})",
				foundPage.isEmpty(), foundPage.isFirst(), foundPage.isLast());
		
		log.info("  2. getNumber({}), getNumberOfElements({}), getSize({})",
						foundPage.getNumber(), foundPage.getNumberOfElements(), foundPage.getSize());
		
		log.info("  3. getSort({}), getTotalElements({}), getTotalPages({})",
				foundPage.getSort(), foundPage.getTotalElements(), foundPage.getTotalPages());

		// ---------
		foundPage.forEach(log::info);
//		foundPage.get().forEachOrdered(log::info);
//		foundPage.toList().forEach(log::info);
//		foundPage.getContent().forEach(log::info);

		// ---------
		foundPage.getContent().clear();
	} // testFindByContentContainingOrderBySeqDesc1
	
//	@Disabled
	@Order(6)
//	@Test
	@RepeatedTest(1)
	@DisplayName("5-2. testFindByContentContainingOrderBySeqDesc2")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByContentContainingOrderBySeqDesc2() {
		log.trace("testFindByContentContainingOrderBySeqDesc2() invoked.");

		// ---------
		String searchContent = "tent_1";
		Pageable currPaging = PageRequest.of(0, 12);
		
		while(true) {
			Page<Board> foundPage = this.boardRepo.findByContentContainingOrderBySeqDesc(searchContent, currPaging);
			log.info("  + currPageNumber: {}/{}, realSize: {}", foundPage.getNumber() + 1, foundPage.getTotalPages(), foundPage.getNumberOfElements());
			
			foundPage.forEach(log::info);
			
			if(foundPage.hasNext()) currPaging = foundPage.nextPageable();
			else break;
		}	// while
	}	// testFindByContentContainingOrderBySeqDesc2
	
//	@Disabled
	@Order(7)
//	@Test
	@RepeatedTest(1)
	@DisplayName("6-1. testFindByTitleContaining1")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByTitleContaining1() {
		log.trace("testFindByTitleContaining1() invoked.");

		// ---------
		String searchTitle = "tle";
		
		int currPage = 0, linesPerPage = 7;
		Pageable paging  = PageRequest.of(currPage, linesPerPage, Sort.Direction.DESC, "seq");

		// ---------
		Page<Board> foundPage = this.boardRepo.findByTitleContaining(searchTitle, paging);
		log.info("  + currPageNumber: {}/{}, realSize: {}", foundPage.getNumber() + 1, foundPage.getTotalPages(), foundPage.getNumberOfElements());
		
		// ---------
		foundPage.forEach(log::info);
	}	// testFindByTitleContaining1
	
//	@Disabled
	@Order(8)
//	@Test
	@RepeatedTest(1)
	@DisplayName("6-2. testFindByTitleContaining2")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByTitleContaining2() {
		log.trace("testFindByTitleContaining2() invoked.");

		// ---------
		String searchTitle = "tle";

		int currPage = 0, linesPerPage = 17;
		Pageable paging = PageRequest.of(currPage, linesPerPage, Sort.Direction.DESC, "seq");

		// ---------
		while(true) {
			Page<Board> foundPage = this.boardRepo.findByTitleContaining(searchTitle, paging);
			log.info("  + currPageNumber: {}/{}, realSize: {}", foundPage.getNumber() + 1, foundPage.getTotalPages(), foundPage.getNumberOfElements());
			
			foundPage.forEach(log::info);
			
			if(foundPage.hasNext()) paging = foundPage.nextPageable();
			else break;
		}	// while
	} 	// testFindByTitleContaining2
	
//	@Disabled
	@Order(9)
//	@Test
	@RepeatedTest(1)
	@DisplayName("7. testFindByTitleAndContent")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByTitleAndContent() {
		log.trace("testFindByTitleAndContent() invoked.");

		// ---------
		String searchTitle = "Title_33", searchContent = "Content_33";
		
		@Cleanup("clear")
		List<Board> foundBoards = this.boardRepo.findByTitleAndContent(searchTitle, searchContent);
		Objects.requireNonNull(foundBoards);

		// ---------
		foundBoards.forEach(log::info);
	} // testFindByTitleAndContent
	
//	@Disabled
	@Order(10)
//	@Test
	@RepeatedTest(1)
	@DisplayName("8. testFindByTitleOrContent")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByTitleOrContent() {
		log.trace("testFindByTitleOrContent() invoked.");

		// ---------
		String searchTitle = "Title_33", searchContent = "Content_77";
		Slice<Board> foundBoards = this.boardRepo.findByTitleOrContent(searchTitle, searchContent);
		
		assert foundBoards != null;
		log.info("  + foundBoards: {}", foundBoards);

		// ---------
		foundBoards.forEach(log::info);
	} // testFindByTitleOrContent
	
//	@Disabled
	@Order(11)
//	@Test
	@RepeatedTest(1)
	@DisplayName("9. testFindBySeqBetween")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindBySeqBetween() {
		log.trace("testFindBySeqBetween() invoked.");

		// ---------
		long startSeq = 33, endSeq = 77;
		int currPage = 0, linesPerPage = 7;

		Pageable paging = PageRequest.of(currPage, linesPerPage, Sort.Direction.DESC, "seq");

		// ---------
		Page<Board> foundPage = this.boardRepo.findBySeqBetween(startSeq, endSeq, paging);
		log.info("  + foundPage: {}", foundPage.getSize());

		// ---------
		foundPage.forEach(log::info);
	} // testFindBySeqBetween
	
//	@Disabled
	@Order(12)
//	@Test
	@RepeatedTest(1)
	@DisplayName("10. testFindBySeqLessThan")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindBySeqLessThan() {
		log.trace("testFindBySeqLessThan() invoked.");

		// ---------
		long criteriaSeq = 77;
		
		Slice<Board> foundSlice = this.boardRepo.findBySeqLessThan(criteriaSeq);
		log.info("  + foundSlice: {}", foundSlice);
		
		// ---------
		foundSlice.forEach(log::info);
	} // testFindBySeqLessThan
	
//	@Disabled
	@Order(13)
//	@Test
	@RepeatedTest(1)
	@DisplayName("11. testFindByTitleLessThanEqual")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByTitleLessThanEqual() {
		log.trace("testFindByTitleLessThanEqual() invoked.");

		// ---------
		String criteriaTitle = "Title_3";
		List<Board> foundList = this.boardRepo.findByTitleLessThanEqual(criteriaTitle);

		// ---------
		foundList.forEach(log::info);
	}	// testFindByTitleLessThanEqual
	
//	@Disabled
	@Order(14)
//	@Test
	@RepeatedTest(1)
	@DisplayName("12. testFindByContentGreaterThan")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByContentGreaterThan() {
		log.trace("testFindByContentGreaterThan() invoked.");

		// ---------
		String criteriaContent = "Content_7";
		
		Slice<Board> foundSlice = this.boardRepo.findByContentGreaterThan(criteriaContent);
		log.info("  + foundSlice: {}", foundSlice);
		
		// ---------
		foundSlice.forEach(log::info);
	}	// testFindByContentGreaterThan
	
//	@Disabled
	@Order(15)
//	@Test
	@RepeatedTest(1)
	@DisplayName("13. testFindBySeqGreaterThanEqual")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindBySeqGreaterThanEqual() {
		log.trace("testFindBySeqGreaterThanEqual() invoked.");

		// ---------
		long criteriaSeq = 77;
		
//		Pageable paging = PageRequest.of(0, 20);
		Pageable paging = PageRequest.of(0, 20, Sort.Direction.DESC, "seq");
		
		Page<Board> foundPage = this.boardRepo.findBySeqGreaterThanEqual(criteriaSeq, paging);
		log.info("  + foundPage: {}", foundPage);

		// ---------
		foundPage.forEach(log::info);
	} // testFindBySeqGreaterThanEqual
	
//	@Disabled
	@Order(16)
//	@Test
	@RepeatedTest(1)
	@DisplayName("14. testFindByCreateDateAfter")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByCreateDateAfter() {
		log.trace("testFindByCreateDateAfter() invoked.");

		// ---------
//		createDate=2024-05-13T09:13:50.301426
		LocalDateTime criteriaCreateDate = LocalDateTime.of(2024, 5, 13, 9, 13, 50, 301426000);
		log.info("  + criteriaCreateDate: {}", criteriaCreateDate);
		
		List<Board> foundList = this.boardRepo.findByCreateDateAfter(criteriaCreateDate);
		log.info("  + foundList: {}", foundList.size());

		// ---------
		foundList.forEach(log::info);
	}	// testFindByCreateDateAfter
	
//	@Disabled
	@Order(17)
//	@Test
	@RepeatedTest(1)
	@DisplayName("15. testFindByCreateDateBefore")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByCreateDateBefore() {
		log.trace("testFindByCreateDateBefore() invoked.");

		// ---------
//		createDate=2024-05-13T09:13:50.301426
		LocalDateTime criteriaCreateDate = LocalDateTime.of(2024, 5, 13, 9, 13, 50, 301426);
		
//		Pageable paging = PageRequest.of(0, 10);
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
		
		Page<Board> foundPage = this.boardRepo.findByCreateDateBefore(criteriaCreateDate, paging);
		log.info("  + foundPage: {}", foundPage);

		// ---------
		foundPage.forEach(log::info);
	}	// testFindByCreateDateBefore
	
//	@Disabled
	@Order(18)
//	@Test
	@RepeatedTest(1)
	@DisplayName("16. testFindByUpdateDateIsNull")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByUpdateDateIsNull() {
		log.trace("testFindByUpdateDateIsNull() invoked.");

		// ---------
		Slice<Board> foundSlice = this.boardRepo.findByUpdateDateIsNull();
		log.info("  + foundSlice: {}", foundSlice.getSize());

		// ---------
		foundSlice.forEach(log::info);
	}	// testFindByCreateDateBefore
	
//	@Disabled
	@Order(19)
//	@Test
	@RepeatedTest(1)
	@DisplayName("17. testFindByCreateDateIsNotNull")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByCreateDateIsNotNull() {
		log.trace("testFindByCreateDateIsNotNull() invoked.");

		// ---------
//		List<Board> foundList = this.boardRepo.findByCreateDateIsNotNull();
		List<Board> foundList = this.boardRepo.findByTitleNotNull();
		
		log.info("  + foundList: {}", foundList.size());

		// ---------
		foundList.forEach(log::info);
	}	// testFindByCreateDateIsNotNull
	
//	@Disabled
	@Order(20)
//	@Test
	@RepeatedTest(1)
	@DisplayName("18. testFindByTitleLike")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByTitleLike() {
		log.trace("testFindByTitleLike() invoked.");

		// ---------
//		String pattern = "Title_1%";				// OK
//		String pattern = "%tle_33";				// OK
		String pattern = "%tle_4%";				// OK
		
		Slice<Board> foundSlice = this.boardRepo.findByTitleLike(pattern);
		log.info("  + foundSlice: {}", foundSlice.getSize());
		
		// ---------
		foundSlice.forEach(log::info);
	}	// testFindByTitleLike
	
//	@Disabled
	@Order(21)
//	@Test
	@RepeatedTest(1)
	@DisplayName("19. testFindByContentNotLike")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByContentNotLike() {
		log.trace("testFindByContentNotLike() invoked.");

		// ---------
//		String pattern = "Content_3%";			// OK
//		String pattern = "%tent_77";				// OK
		String pattern = "%tent_1%";				// OK
		
		List<Board> foundList = this.boardRepo.findByContentNotLike(pattern);
		log.info("  + foundList: {}", foundList.size());

		// ---------
		foundList.forEach(log::info);
	}	// testFindByContentNotLike
	
//	@Disabled
	@Order(22)
//	@Test
	@RepeatedTest(1)
	@DisplayName("20. testFindByWriterStartingWith")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByWriterStartingWith() {
		log.trace("testFindByWriterStartingWith() invoked.");

		// ---------
		String startingWriter = "Yo";
		
		Slice<Board> foundSlice = this.boardRepo.findByWriterStartingWith(startingWriter);
		log.info("  + foundSlice: {}", foundSlice.getSize());

		// ---------
		foundSlice.forEach(log::info);
	}	// testFindByWriterStartingWith
	
//	@Disabled
	@Order(23)
//	@Test
	@RepeatedTest(1)
	@DisplayName("21. testFindByWriterEndingWith")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByWriterEndingWith() {
		log.trace("testFindByWriterEndingWith() invoked.");

		// ---------
		String endingWriter = "ph";
		
		Slice<Board> foundSlice = this.boardRepo.findByWriterEndingWith(endingWriter);
		log.info("  + foundSlice: {}", foundSlice.getSize());

		// ---------
		foundSlice.forEach(log::info);
	}	// testFindByWriterEndingWith
	
//	@Disabled
	@Order(24)
//	@Test
	@RepeatedTest(1)
	@DisplayName("22. testFindBySeqNot")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindBySeqNot() {
		log.trace("testFindBySeqNot() invoked.");

		// ---------
		long criteriaSeq = 77;
		
		List<Board> foundList = this.boardRepo.findBySeqNot(criteriaSeq);
		log.info("  + foundList: {}", foundList.size());

		// ---------
		foundList.forEach(log::info);
	}	// testFindBySeqNot
	
//	@Disabled
	@Order(25)
//	@Test
	@RepeatedTest(1)
	@DisplayName("23. testFindBySeqIn")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindBySeqIn() {
		log.trace("testFindBySeqIn() invoked.");

		// ---------
		List<Long> seqs = Arrays.<Long>asList(3L, 33L, 7L, 77L);
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
		
		Page<Board> foundPage = this.boardRepo.findBySeqIn(seqs, paging);
		log.info("  + foundPage: {}", foundPage);

		// ---------
		foundPage.forEach(log::info);
	}	// testFindBySeqIn
	
//	@Disabled
	@Order(26)
//	@Test
	@RepeatedTest(1)
	@DisplayName("24. testFindByTitleLikeOrContentLikeOrderByCreateDateDescSeqAsc")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByTitleLikeOrContentLikeOrderByCreateDateDescSeqAsc() {
		log.trace("testFindByTitleLikeOrContentLikeOrderByCreateDateDescSeqAsc() invoked.");

		// ---------
		String titlePattern = "__tle_2%", contentPattern = "Con%7%";
		
		List<Board> foundList = this.boardRepo.findByTitleLikeOrContentLikeOrderByCreateDateDescSeqAsc(titlePattern, contentPattern);
		log.info("  + foundList: {}", foundList.size());

		// ---------
		foundList.forEach(log::info);
	}	// testFindByTitleLikeOrContentLikeOrderByCreateDateDescSeqAsc
	
	

} // end class


