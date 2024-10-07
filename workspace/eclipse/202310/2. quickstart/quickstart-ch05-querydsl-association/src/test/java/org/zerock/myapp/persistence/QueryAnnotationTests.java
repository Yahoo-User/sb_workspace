package org.zerock.myapp.persistence;

import java.util.List;
import java.util.Objects;

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
public class QueryAnnotationTests {
	@Autowired
	private BoardRepository boardRepo;
	
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("testQueryAnnotation1")
	@Timeout(1L)
	void testQueryAnnotation1() {
		log.trace("testQueryAnnotation1() inoked.");
		
		Objects.requireNonNull(this.boardRepo);
		
		List<Board> foundBoards = this.boardRepo.queryAnnotation1("NEW");
		foundBoards.forEach(log::info);
	} // testQueryAnnotation1
	
	
////	@Disabled
//	@Test
//	@Order(2)
//	@DisplayName("testQueryAnnotation2")
//	@Timeout(1L)
//	void testQueryAnnotation2() {
//		log.trace("testQueryAnnotation2() inoked.");
//		
//		Objects.requireNonNull(this.boardRepo);
//		
//		List<Object[]> foundBoards = this.boardRepo.queryAnnotation2("NEW");
//		foundBoards.forEach(arr -> log.info(Arrays.toString(arr)));
//	} // testQueryAnnotation2
	
	
////	@Disabled
//	@Test
//	@Order(3)
//	@DisplayName("testQueryAnnotation3")
//	@Timeout(1L)
//	void testQueryAnnotation3() {
//		log.trace("testQueryAnnotation3() inoked.");
//		
//		Objects.requireNonNull(this.boardRepo);
//		
//		List<Object[]> foundBoards = this.boardRepo.queryAnnotation3("NEW");
//		foundBoards.forEach(arr -> log.info(Arrays.toString(arr)));
//	} // testQueryAnnotation3
	
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("testQueryAnnotation4")
	@Timeout(1L)
	void testQueryAnnotation4() {
		log.trace("testQueryAnnotation4() inoked.");
		
		Objects.requireNonNull(this.boardRepo);
		
		Pageable paging = PageRequest.of(0, 5);
		List<Board> foundBoards = this.boardRepo.queryAnnotation4(paging);
		foundBoards.forEach(log::info);
	} // testQueryAnnotation4
	

} // end class
