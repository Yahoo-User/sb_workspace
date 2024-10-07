package org.zerock.myapp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.service.BoardService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@SessionAttributes("member")
@Controller
//@RestController
public class BoardController
	implements InitializingBean {
	
	@Setter(onMethod_= {@Autowired})
	private BoardService service;
	
	
	@ModelAttribute("member")
	Member intializeSession() {
		log.debug("intializeSession() invoked.");
		
		return new Member();
	} // intializeSession
	
	@GetMapping("/hello")
	public String hello(Model model) {
		log.debug("hello({}) invoked.", model);
		
		model.addAttribute("greeting", "hello");
		log.info("\t+ model: {}", model);
		
		return "hello";
	} // hello
	
	@RequestMapping("/getBoardList")
	public String getBoardList(@ModelAttribute("member") Member member, Model model) {
		log.debug("getBoardList({}, {}) invoked.", member, model);
		
		if(member.getId() != null) {
			List<Board> boardList = this.service.getBoardList();
			model.addAttribute("boardList", boardList);
			
			return "getBoardList";
		} else {
			return "redirect:/login";
		} // if-else
	} // getBoardList
	
	
	@PostMapping("/insertBoard")
	String insertBoard() {
		log.debug("insertBoard() invoked.");
		
		Board board = new Board();
		
		board.setContent("CONTENT_NEW");
		board.setTitle("TITLE_NEW");
		board.setWriter("member2");
		board.setCreateDate(new Date());
		board.setCnt(0l);
		
		this.service.insertBoard(board);
		
//		return "insertBoard";
		return "redirect:/getBoardList";
	} // insertBoard
	
	
	@GetMapping("/getBoard")
	String getBoard(Long seq, Model model) {
		log.debug("getBoard({}) invoked.", seq);
		
		Board board = this.service.getBoard(seq);
		log.info("\t+ board: {}", board);
		
		model.addAttribute("board", board);
		
//		return "redirect:/getBoardList";
		return "getBoard";
	} // getBoard
	
	
	@PostMapping("/updateBoard")
	String updateBoard(Board board) {
		log.debug("updateBoard({}) invoked.", board);
		
//		board.setUpdateDate(new Date());
		this.service.updateBoard(board);
		
		return "redirect:/getBoardList";
	} // updateBoard
	
	
	@PostMapping("/deleteBoard")
	String deleteBoard(Long seq) {
		log.debug("deleteBoard({}) invoked", seq);
		
		this.service.deleteBoard(seq);
		
		return "redirect:/getBoardList";
	} // deleteBoard

	
	
//	-----------------------

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
		
		assert this.service != null;
		log.info("\t+ service: {}, type: {}", this.service, this.service.getClass().getName());
	} // afterPropertiesSet

} // end class
