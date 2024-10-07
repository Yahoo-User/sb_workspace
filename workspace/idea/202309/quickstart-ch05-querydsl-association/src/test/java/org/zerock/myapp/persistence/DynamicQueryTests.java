package org.zerock.myapp.persistence;

import com.querydsl.core.BooleanBuilder;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.QBoard;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
public class DynamicQueryTests {
	@Autowired private DynamicBoardRepository dynamicBoardRepository;
	
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assert this.dynamicBoardRepository != null;
		log.info("\t+ this.dynamicBoardRepository: {}", this.dynamicBoardRepository);
	} // beforeAll
	
	
//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("testDynamicQuery")
	@Timeout(1L)
	void testDynamicQuery() {
		log.trace("testDynamicQuery() invoked.");

//		================================================
		
		String title = "TL_10";
		String content = "CONTENT";

		BooleanBuilder builder = new BooleanBuilder();
		log.info("\t1. builder: {}", builder);
		
		QBoard qBoard = QBoard.board;
		log.info("\t2. qBoard: {}, type: {}", qBoard, qBoard.getClass().getName());
		
		if("TL_10".equals(title)) {
			builder.and(qBoard.title.like("%" + title + "%"));
		} else if("CONTENT".equals(content)) {
			builder.and(qBoard.content.like("%" + content + "%"));
		} // if-else if

		builder.or(qBoard.cnt.goe(0));		// OR cnt >= 0

//		================================================

		int page = 0;
		int size = 50;

//		Pageable paging = PageRequest.of(page, size);
		Pageable paging = PageRequest.of(page, size, Sort.Direction.DESC, "seq");
		log.info("\t3. paging: {}", paging);

		Page<Board> boardList = this.dynamicBoardRepository.findAll(builder, paging);
		boardList.forEach(log::info);
	} /* testDynamicQuery */

} // end class
