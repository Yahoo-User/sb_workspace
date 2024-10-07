package org.zerock.myapp.seq_9_3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.seq_0.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_4.domain.Board;
import org.zerock.myapp.seq_7.service.BoardService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/board")				// Base URI
@Controller("boardController4")
public class BoardController extends CommonBeanCallbacks {
	@Autowired(required = true) private BoardService boardService;
	
		
	@GetMapping("/registerWithDTO")
	void registerWithDTO() {
		log.trace("registerWithDTO() invoked.");
	} // registerWithDTO
	
	@PostMapping(path="/registerWithDTO", params = { "title", "writer", "content" })
//	String registerProcessWithDTO(BoardDTO dto, RedirectAttributes rttrs) {
	String registerProcessWithDTO(Board dto, Integer currPage, RedirectAttributes rttrs) {
		log.trace("registerProcessWithDTO({}, {}, {}) invoked.", dto, currPage, rttrs);

		// ------
		if(currPage == null) currPage = 1;
		
		// ------
		Board transientBoard = new Board();
		
		transientBoard.setTitle(dto.getTitle());
		transientBoard.setWriter(dto.getWriter());
		transientBoard.setContent(dto.getContent());

		// ------
		Boolean isCreated = this.boardService.createBoard(transientBoard);
		log.info("  + isCreated({}), board({})", isCreated, transientBoard);

		// ------
		// (1) rtts.addFlashAttribute(key, val) 	=> Set attribute to the `Request Scope` as a shared attribute.
		// (2) rtts.addAttribute(key, val) 			=> Set attribute to the request parameter with GET method in the `Query String`.
		// ------
		rttrs.addFlashAttribute("isCreated", isCreated);							// (1)
		rttrs.addFlashAttribute("seq", transientBoard.getSeq());				// (1)
		rttrs.addFlashAttribute("currPage", currPage);							// (1)
		
		rttrs.addAttribute("isCreated", isCreated);									// (2)
		rttrs.addAttribute("seq", transientBoard.getSeq());						// (2)
		rttrs.addAttribute("currPage", currPage);										// (2)

		// -----
		return "redirect:getBoardList";				// OK, Re-direct with GET method
//		return "forward:getBoardList";				// OK, Forward with POST method
//		return "board/getBoardList";				// XX, Forward with BaseURI & POST method (Default)
	} // registerProcessWithDTO
	
} // end class

