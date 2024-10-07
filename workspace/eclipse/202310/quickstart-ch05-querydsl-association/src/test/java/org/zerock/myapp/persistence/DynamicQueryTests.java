package org.zerock.myapp.persistence;

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
import org.springframework.data.domain.Sort;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.QBoard;

import com.querydsl.core.BooleanBuilder;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@SpringBootTest

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DynamicQueryTests {
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assert this.boardRepo != null;
		log.info("\t+ this.boardRepo: {}", this.boardRepo);
	} // beforeAll
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testDynamicQuery")
	@Timeout(1L)
	void testDynamicQuery() {
		log.trace("testDynamicQuery() invoked.");
		
		String searchCondition = "WRITER";
		String searchKeyword = "Yoseph";
		
//		================================================
		BooleanBuilder builder = new BooleanBuilder();
		log.info("\t1. builder: {}", builder);
		
		QBoard qBoard = QBoard.board;
		log.info("\t2. qBoard: {}, type: {}", qBoard, qBoard.getClass().getName());
//		================================================
		
		if("TITLE".equals(searchCondition)) {
			builder.and(qBoard.title.like("%" + searchKeyword + "%"));
		} else if("CONTENT".equals(searchCondition)) {
			builder.and(qBoard.content.like("%" + searchKeyword + "%"));
		} else if("WRITER".equals(searchCondition)) {
//			builder.and(qBoard.writer.like("%" + searchKeyword + "%"));
		} // if-else if-else if
		
		builder.or(qBoard.cnt.goe(0));		// OR cnt >= 0

//		================================================
		int currPage = 0;
		int linesPerPage = 50;
		
//		Pageable paging = PageRequest.of(currPage, linesPerPage);
		Pageable paging = PageRequest.of(currPage, linesPerPage, Sort.Direction.DESC, "seq");
		log.info("\t3. paging: {}", paging);
//		================================================
		
		Page<Board> boardList = this.boardRepo.findAll(builder, paging);
		boardList.forEach(log::info);		
	} // testDynamicQuery

} // end class
