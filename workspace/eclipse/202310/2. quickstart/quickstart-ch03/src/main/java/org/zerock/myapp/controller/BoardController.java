package org.zerock.myapp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.BoardVOWithLombokValue;
import org.zerock.myapp.domain.BoardVOWithRecordType;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@RestController = (1) @ResponseBody + (2) @Controller
@RestController
public class BoardController {

	// `@AliasFor` is an annotation that is used to declare aliases for annotation attributes.
	
	// @RequestMapping(method = RequestMethod.GET)
	@GetMapping("/hello")
	public String hello(String name) {
		log.trace("hello({}) invoked.", name);
		return "Hello, %s".formatted(name);
	} // hello
	
	@GetMapping("/getBoard")
	public BoardVOWithRecordType getBoard() {
		log.trace("getBoard() invoked.");
		
		BoardVOWithRecordType vo = new BoardVOWithRecordType(1, "title", "writer", "content", LocalDateTime.now(), 0);
//		BoardVOWithRecordType vo = new BoardVOWithRecordType();			// OK : with non-canonical constructor
		
		log.info("\t+ vo: {}", vo);
		
		return vo;
	} // getBoard
	
	@GetMapping("/getBoard2")
	public BoardVOWithLombokValue getBoard2() {
		log.trace("getBoard2() invoked.");
		
		BoardVOWithLombokValue vo = new BoardVOWithLombokValue(2, "title_2", "writer_2", "content_2", LocalDateTime.now(), 1);
		log.info("\t+ vo: {}", vo);
		
		return vo;
	} // getBoard2
	
	@GetMapping("/getBoardList")
	public List<BoardVOWithRecordType> getBoardList() {
		log.trace("getBoardList() invoked.");
		
		List<BoardVOWithRecordType> list = new ArrayList<>();
		
		for(int i=0; i < 10; i++) {
			BoardVOWithRecordType vo = new BoardVOWithRecordType(i, "title_"+i, "writer_"+i, "content_"+i, LocalDateTime.now(), 0);
			list.add(vo);
		} // for
		
		return list;
	} // getBoardList
	
	@GetMapping("/getBoardList2")
	public List<BoardVOWithLombokValue> getBoardList2() {
		log.trace("getBoardList2() invoked.");
		
		List<BoardVOWithLombokValue> list = new ArrayList<>();
		
		for(int i=0; i < 10; i++) {
			BoardVOWithLombokValue vo2 = new BoardVOWithLombokValue(i, "title_"+i, "writer_"+i, "content_"+i, LocalDateTime.now(), 0);
			list.add(vo2);
		} // for
		
		return list;
	} // getBoardList2
	
	
	/*
	 * ------------------------------
	 * @PostConstruct
	 * ------------------------------
	 * The annotation is used on a method that needs to be executed after dependency injection is done to performany initialization.
	 * This method must be invoked before the class is put into service.
	 * This annotation must be supported on all classes that support dependency injection.
	 * The method annotated with PostConstruct must be invoked even if the class does not request any resources to be injected.
	 */
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
	} // postConstruct
	
	@PreDestroy
	void preDestroy() {
		log.trace("preDestroy() invoked.");
	} // preDestroy
	
} // end class
