package org.zerock.myapp.seq_9_4.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.seq_0.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_4.domain.Board;
import org.zerock.myapp.seq_7.service.BoardService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/board")				// Base URI
@Controller("boardController5")
public class BoardController extends CommonBeanCallbacks {
	@Autowired(required = true) private BoardService boardService;
	
		
	@GetMapping("/getBoard")
	void getBoard(Long seq, Model model) {
		log.trace("getBoard({}, {}) invoked.", seq, model);
		
		// -----
		Board foundBoard = this.boardService.findBoardById(seq);
		
		// -----
		Objects.requireNonNull(foundBoard);
		model.addAttribute("_BOARD_", foundBoard);
		
		// -----
		foundBoard.setCnt( foundBoard.getCnt() + 1 );

		// -----
		this.boardService.modifyBoard(foundBoard);
	} // getBoard
	
} // end class

