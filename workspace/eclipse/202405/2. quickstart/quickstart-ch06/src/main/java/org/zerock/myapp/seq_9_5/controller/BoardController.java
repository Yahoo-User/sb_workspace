package org.zerock.myapp.seq_9_5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@Controller("boardController6")
public class BoardController extends CommonBeanCallbacks {
	@Autowired(required = true) private BoardService boardService;
	
		
	@PostMapping(path="/update", params = { "seq", "title", "writer", "content" })
	String updateProcess(Board dto, Integer currPage, RedirectAttributes rttrs) {
		log.trace("updateProcess({}, {}, {}) invoked.", dto, currPage, rttrs);
		
		// -----
		Board foundBoard = this.boardService.findBoardById(dto.getSeq());
		
		// -----
		if(foundBoard != null) {
			foundBoard.setTitle(dto.getTitle());
			foundBoard.setWriter(dto.getWriter());
			foundBoard.setContent(dto.getContent());
			foundBoard.setCnt(foundBoard.getCnt() + 1);
	
			this.boardService.modifyBoard(foundBoard);
		} else log.info("  + *Not Found.");

		// -----
		if(currPage == null) currPage = 1;
		rttrs.addAttribute("currPage", currPage);

		// -----
		return "redirect:getBoardList";				// OK, Re-direct with GET method
//		return "forward:getBoardList";				// OK, Forward with POST method
//		return "board/getBoardList";				// XX, Forward with BaseURI & POST method (Default)
	} // updateProcess
	
} // end class

