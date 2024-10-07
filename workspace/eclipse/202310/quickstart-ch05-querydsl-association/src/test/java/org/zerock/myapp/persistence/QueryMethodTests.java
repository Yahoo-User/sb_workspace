package org.zerock.myapp.persistence;

import java.util.List;
import java.util.Objects;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zerock.myapp.domain.Board;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@SpringBootTest

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QueryMethodTests {
	@Autowired private BoardRepository boardRepo;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assert this.boardRepo != null;
		log.info("\t+ this.boardRepo: {}", this.boardRepo);
		
//		for(int i=1; i<= 200; i++) {
//			Board board = new Board();
//			board.setTitle("TITLE_"+i);
//			
//			if(i % 2 == 0) board.setWriter("Yoseph");
//			else board.setWriter("Pyramide");
//			
//			board.setContent("CONTENT_"+ i);
//			
//			this.boardRepo.save(board);
//		} // for
	} // beforeAll
	
	
////	@Disabled
//	@Test
//	@Order(1)
//	@DisplayName("testFindByWriter")
//	@Timeout(1L)
//	void testFindByWriter() {
//		log.trace("testFindByWriter() invoked.");
//		
//		List<Board> boardList = this.boardRepo.findByWriter("Yoseph");
//		boardList.forEach(log::info);
//	} // testFindByWriter
	
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("testFindByTitle")
	@Timeout(1L)
	void testFindByTitle() {
		log.trace("testFindByTitle() invoked.");
		
		List<Board> boardList = this.boardRepo.findBoardByTitle("NEW");
		boardList.forEach(log::info);
	} // testFindByTitle
	
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("testFindByContentContaining")
	@Timeout(1L)
	void testFindByContentContaining() {
		log.trace("testFindByContentContaining() invoked.");
		
		List<Board> boardList = this.boardRepo.findByContentContaining("CONTENT");
		
		Objects.requireNonNull(boardList);
		boardList.forEach(log::info);
	} // testFindByContentContaining
	
	
////	@Disabled
//	@Test
//	@Order(4)
//	@DisplayName("testFindByTitleContainingAndWriterContaining")
//	@Timeout(1L)
//	void testFindByTitleContainingAndWriterContaining() {
//		log.trace("testFindByTitleContainingAndWriterContaining() invoked.");
//		
//		List<Board> boardList = this.boardRepo.findByTitleContainingAndWriterContaining("NEW", "Yoseph");
//		
//		Objects.requireNonNull(boardList);
//		boardList.forEach(log::info);
//	} // testFindByTitleContainingAndWriterContaining
	
	
////	@Disabled
//	@Test
//	@Order(5)
//	@DisplayName("testFindByTitleContainingOrContentContaining")
//	@Timeout(1L)
//	void testFindByTitleContainingOrContentContaining() {
//		log.trace("testFindByTitleContainingOrContentContaining() invoked.");
//		
//		List<Board> boardList = this.boardRepo.findByTitleContainingOrContentContaining("NEW", "CONTENT");
//		
//		Objects.requireNonNull(boardList);
//		boardList.forEach(log::info);
//	} // testFindByTitleContainingAndWriterContaining
	
	
//	@Disabled
	@Test
	@Order(6)
	@DisplayName("testFindByTitleContainingOrderBySeqDesc")
	@Timeout(1L)
	void testFindByTitleContainingOrderBySeqDesc() {
		log.trace("testFindByTitleContainingOrderBySeqDesc() invoked.");
		
		List<Board> boardList = this.boardRepo.findByTitleContainingOrderBySeqDesc("NEW");
		
		Objects.requireNonNull(boardList);
		boardList.forEach(log::info);
	} // testFindByTitleContainingOrderBySeqDesc
	
	
//	@Disabled
//	@Test
//	@Order(7)
//	@DisplayName("testFindByTitleContainingbyPaging")
//	@Timeout(1L)
//	void testFindByTitleContainingbyPaging() {
//		log.trace("testFindByTitleContainingbyPaging() invoked.");
//
//		Pageable paging = PageRequest.of(0, 5);		// 1페이지, 5개만 보여주기
////		Pageable paging = PageRequest.of(1, 5);		// 2페이지, 5개만 보여주기
//		
//		List<Board> boardList = this.boardRepo.findByTitleContaining("NEW", paging);
//		
//		Objects.requireNonNull(boardList);
//		boardList.forEach(log::info);
//	} // testFindByTitleContainingbyPaging
	
	
//	@Disabled
//	@Test
//	@Order(8)
//	@DisplayName("testFindByTitleContainingbyPagingAndSorting")
//	@Timeout(1L)
//	void testFindByTitleContainingbyPagingAndSorting() {
//		log.trace("testFindByTitleContainingbyPagingAndSorting() invoked.");
//
////		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");					// Paging and ORDER BY seq DESC
//		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq", "createDate");	// Paging and ORDER BY seq DESC, createDate DESC
//		
//		List<Board> boardList = this.boardRepo.findByTitleContaining("NEW", paging);
//		
//		Objects.requireNonNull(boardList);
//		boardList.forEach(log::info);
//	} // testFindByTitleContainingbyPagingAndSorting
	
	
//	@Disabled
	@Test
	@Order(9)
	@DisplayName("testFindByTitleContainingByPage")
	@Timeout(1L)
	void testFindByTitleContainingByPage() {
		log.trace("testFindByTitleContainingByPage() invoked.");

		Pageable paging = PageRequest.of(0, 5);												// Just Paging
//		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");					// Paging and ORDER BY seq DESC
//		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq", "createDate");	// Paging and ORDER BY seq DESC, createDate DESC
		
		Page<Board> pageInfo = this.boardRepo.findByTitleContaining("NEW", paging);
		
		Objects.requireNonNull(pageInfo);
		log.info("\t+ pageInfo: {}", pageInfo);
		
		log.info("\t+ number({}), size({}), totalPages({}), NumberOfElements({}), totalElements({}), hasNext({}), hasPreviois({}), isLast({}), nextPageable({}), previousPageable({}), hasContent({})", 
				pageInfo.getNumber(),			// Current Page No.
				pageInfo.getSize(),				// Size of One Page.
				pageInfo.getTotalPages(),		// Number of Total Pages.
				pageInfo.getNumberOfElements(),	// The number of result.
				pageInfo.getTotalElements(),	// Total amount of elements.
				pageInfo.hasNext(),				// Returns if there is a next Slice.
				pageInfo.hasPrevious(),			// Returns if there is a previous Slice.
				pageInfo.isLast(),				// Returns whether the current Slice is the last one.
				pageInfo.nextPageable(),		// Returns the Pageable to request the next Slice.
				pageInfo.previousPageable(),	// Returns the Pageable to request the previous Slice.
				pageInfo.hasContent());			// Returns whether the Slice has content at all.
		
		List<Board> boardList = pageInfo.getContent();
		boardList.forEach(log::info);
	} // testFindByTitleContainingByPage
	
	

} // end class
