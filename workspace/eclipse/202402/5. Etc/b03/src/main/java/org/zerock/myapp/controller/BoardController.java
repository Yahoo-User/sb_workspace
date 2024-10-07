package org.zerock.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.service.BoardService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor



//@Controller
//@ResponseBody

// @RestController == @Controller + @ResponseBody
@RestController
public class BoardController implements InitializingBean, DisposableBean {
	
	@Autowired
	private BoardService service;
	
	
	@RequestMapping("/hello")
//	@GetMapping(path="/hello")
//	@RequestMapping(path="/hello", method=RequestMethod.GET, params= {"name", "age"})
	public String hello(String name, Integer age) {
		log.info("hello({}, {}) invoked.", name, age);
		
//		return "Hello, "+name;
		return this.service.hello(name, age);
	} // hello
	

	@RequestMapping("/getBoard")
//	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		log.info("getBoard() invoked.");
		
//		BoardVO board = new BoardVO();
//		board.setSeq(1);
//		board.setTitle("테스트 제목...");
//		board.setWriter("테스터");
//		board.setContent("테스트 내용입니다.....");
//		board.setCreateDate(new Date());
//		board.setCnt(0);
//		
//		return board;
		
		return this.service.getBoard();
	} // getBoard
	

	@RequestMapping("/getBoardList")
//	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() {
		log.info("getBoardList() invoked.");
		
//		List<BoardVO> boardList = new ArrayList<>(10);
//		
//		for(int i=0;i<10;i++) {
//			BoardVO board = new BoardVO();
//			
//			board.setSeq(i);
//			board.setTitle("테스트 제목..."+i);
//			board.setWriter("테스터"+i);
//			board.setContent("테스트 내용입니다.....");
//			board.setCreateDate(new Date());
//			board.setCnt(0);
//			
//			boardList.add(board);
//		} // for
//		
//		return boardList;
		
		return this.service.getBoardList();
	} // getBoardList

	
//	--------------------------------------
	
	@Override
	public void destroy() throws Exception {
		log.info("destroy() invoked.");
		
	} // destroy


	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("afterPropertiesSet() invoked.");
		
		log.info("\t+ service: {}", this.service);
	} // afterPropertiesSet

} // end class
