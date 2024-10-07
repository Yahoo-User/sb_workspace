package org.zerock.myapp.seq_9_1.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.seq_0.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_4.domain.Board;
import org.zerock.myapp.seq_4.domain.Pagination;
import org.zerock.myapp.seq_7.service.BoardService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/board")				// Base URI
@Controller("boardController2")
public class BoardController extends CommonBeanCallbacks {
	@Autowired(required = true) private BoardService boardService;
	
	
	@RequestMapping(path="/getBoardList", method = {})
	String getBoardList(Integer currPage, Model model) {
		log.trace("getBoardList({}, {}) invoked.", currPage, model);

		// -------
		if(currPage == null) currPage = 1;
		
		// -------
		Page<Board> foundPage = this.boardService.findAllBoards(currPage);
		
		Objects.requireNonNull(foundPage);
		Pagination pageDTO = new Pagination(foundPage);
		log.info("\t+ pageDTO: {}", pageDTO);

		/*
		// -------
		// Pagination Information
		// -------
		log.info("  + getTotalPages: {}", foundPage.getTotalPages());
		log.info("  + getTotalElements: {}", foundPage.getTotalElements());
		log.info("  + getNumber: {}", foundPage.getNumber());
		log.info("  + isFirst: {}", foundPage.isFirst());
		log.info("  + isLast: {}", foundPage.isLast());
		log.info("  + hasPrevious: {}", foundPage.hasPrevious());
		log.info("  + hasNext: {}", foundPage.hasNext());
		log.info("  + previousPageable: {}", foundPage.previousPageable());
		log.info("  + previousOrFirstPageable: {}", foundPage.previousOrFirstPageable());
		log.info("  + nextPageable: {}", foundPage.nextPageable());
		log.info("  + nextOrLastPageable: {}", foundPage.nextOrLastPageable());
		*/
		
		// -------
		model.addAttribute("_PAGE_", foundPage);
		model.addAttribute("_PAGINATION_", pageDTO);

		// -------
		return "board/getBoardList";	// return viewName	-> Prefix + viewName + Suffix	-> Resolved View Name
	} 	// getBoardList
	
} // end class

