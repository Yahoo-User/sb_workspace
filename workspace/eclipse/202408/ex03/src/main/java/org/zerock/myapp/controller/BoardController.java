package org.zerock.myapp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.BoardVO2;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RestController		//@RestController = (1) @ResponseBody + (2) @Controller
public class BoardController {
	
	
	// @AliasFor is an annotation that is used to declare aliases for annotation attributes. 
	@GetMapping("/hello")		// @RequestMapping(method = RequestMethod.GET)
	public String hello(String name) {
		log.trace("helllo({}) invoked.", name);
		
		return "Hello, %s".formatted(name);
	} // hello
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		log.trace("getBoard() invoked.");
		
		// The record has a all args constructor by default.								<--- ***
		BoardVO vo = new BoardVO(1, "title", "writer", "content", LocalDateTime.now(), 0);
		
		// The record can have a non-cannoical constructor if needed.				<--- ***
//		BoardVO vo = new BoardVO();
		
		log.info("\t+ vo: {}", vo);
		
		// The record has a fluent getter method for each field by default.			<--- ***
		log.info("\t+ seq({}), title({}), content({}), createDate({}), cnt({})", vo.seq(), vo.title(), vo.content(), vo.createDate(), vo.cnt());
		
		return vo;
	} // getBoard
	
	@GetMapping("/getBoard2")
	public BoardVO2 getBoard2() {
		log.trace("getBoard2() invoked.");
		return new BoardVO2(2, "title_2", "writer_2", "content_2",  LocalDateTime.now(), 0);
	} // getBoard2
	
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() {
		log.trace("getBoardList() invoked.");
		
		List<BoardVO> list = new ArrayList<>();
		
		for(int i=0; i < 10; i++) {
			BoardVO vo = new BoardVO(i, "title_"+i, "writer_"+i, "content_"+i, LocalDateTime.now(), 0);
			list.add(vo);
		} // for
		
		return list;
	} // getBoardList
	
	@GetMapping("/getBoardList2")
	public List<BoardVO2> getBoardList2() {
		log.trace("getBoardList2() invoked.");
		
		List<BoardVO2> list = new ArrayList<>();
		
		for(int i=0; i < 10; i++) {
			BoardVO2 vo2 = new BoardVO2(i, "title_"+i, "writer_"+i, "content_"+i, LocalDateTime.now(), 0);
			list.add(vo2);
		} // for
		
		return list;
	} // getBoadList2
	
	
} // end class
