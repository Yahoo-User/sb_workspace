package org.zerock.myapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.BoardVO;

import lombok.extern.log4j.Log4j2;


@Log4j2
//@NoArgsConstructor

// @RestController == @Controller + @ResponseBody
@RestController

//@Controller
//@ResponseBody
public class BoardController {
	
	
	public BoardController() {
		log.info("Default Constructor invoked.");
	} // default constructor
	
	
	@GetMapping(path="/hello")
//	@RequestMapping(path="/hello", method=RequestMethod.GET, params= {"name", "age"})
	public String hello(String name, Integer age) {
		log.info("hello({}, {}) invoked.", name, age);
		
		return "Hello, "+name;
	} // hello
	
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		log.info("getBoard() invoked.");
		
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("테스트 제목...");
		board.setWriter("테스터");
		board.setContent("테스트 내용입니다.....");
		board.setCreateDate(new Date());
		board.setCnt(0);
		
		return board;
	} // getBoard
	
	
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() {
		log.info("getBoardList() invoked.");
		
		List<BoardVO> boardList = new ArrayList<>(10);
		
		for(int i=0;i<10;i++) {
			BoardVO board = new BoardVO();
			
			board.setSeq(i);
			board.setTitle("테스트 제목..."+i);
			board.setWriter("테스터"+i);
			board.setContent("테스트 내용입니다.....");
			board.setCreateDate(new Date());
			board.setCnt(0);
			
			boardList.add(board);
		} // for
		
		return boardList;
	} // getBoardList

} // end class
