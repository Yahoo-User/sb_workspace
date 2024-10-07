package org.zerock.myapp.seq_2.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.seq_0.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_1.domain.BoardVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/board")				// Base URI
@Controller("boardController1")
public class BoardController extends CommonBeanCallbacks {
	
	
	@GetMapping("/findAllBoards")																				// Detail URI
//	@RequestMapping(path = "/findAllBoards", method = RequestMethod.GET )		// Detail URI
	String findAllBoards(Model model) {
		log.trace("findAllBoards({}, type: {}) invoked.", model, model.getClass().getName());
		
		// -------
		List<BoardVO> findAllBoards = new ArrayList<>();
		
		IntStream.rangeClosed(1, 20).forEachOrdered(seq -> {
			log.trace("  forEachOrdered(%02d) invoked.".formatted(seq));
			
			BoardVO vo = new BoardVO(0L+seq, "Title_"+seq, "Yoseph", "Content_"+seq, 0, new Date(), null);
			findAllBoards.add(vo);
		});		// .forEachOrdered

		// -------
		model.addAttribute("_BOARD_PAGE_", findAllBoards);

		// -------
		return "board/findAllBoards";		// return viewName	-> Prefix + viewName + Suffix	-> Resolved View Name
	} // findAllBoards
	
} // end class

