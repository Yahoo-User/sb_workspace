package org.zerock.myapp.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.common.CommonBeanCallbacks;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.service.BoardService;
import org.zerock.myapp.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@SessionAttributes(SharedAttributes.CREDENTIAL)

@RequestMapping("/board")				// Base URI
@Controller("boardController")
public class BoardController extends CommonBeanCallbacks {
	@Autowired(required = true) private BoardService boardService;
	

	/**
	 * *Important:
	 * 		This method wouldn't be invoked
	 * 		if the specified shared attribute with @ModelAttribute annotation already exists.
	 */
	@ModelAttribute(SharedAttributes.CREDENTIAL)
	public Member createEmptyCredential() {
		log.info("&&& createEmptyCredential() invoked.");
		return new Member();
	} // createEmptyCredential
	
	
	@GetMapping(path="/getBoardList")
	String getBoardList(
		@ModelAttribute(SharedAttributes.CREDENTIAL) Member member, 			//	1, OK
//		@SessionAttribute(SharedAttributes.CREDENTIAL) Member member,		//	2, XX - Missing session attribute '_MEMBER_' of type Member
		Integer currPage, Model model
	) {
		log.trace("getBoardList({}, {}, {}) invoked.", member, currPage, model);

		// -------
		if(member == null || member.getId() == null) {
//			return "redirect:security/login";		// XX, returned view name = /board/security/login
			return "redirect:/security/login";			// OK
		} // if
		
		// -------
		if(currPage == null) currPage = 1;
		Page<Board> foundPage = this.boardService.findAllBoards(currPage);
		
		model.addAttribute(SharedAttributes.PAGE, foundPage);

		// -------
		return "board/getBoardList";
	} 	// getBoardList
	
	
	@GetMapping("/register")
	String register(@ModelAttribute(SharedAttributes.CREDENTIAL) Member member) {
		log.trace("register({}) invoked.", member);

		// -------
		if(member == null || member.getId() == null) {
			return "redirect:/security/login";
		} else {
//			return "register";						// XX
			return "board/register";			// OK
		} // if-else
	} // register
	
	@PostMapping(path="/register", params = { "title", "writer", "content" })
	String registerProcess(
		@ModelAttribute(SharedAttributes.CREDENTIAL) Member member,
		@RequestParam("title") String title2, String writer, String content, Integer currPage, RedirectAttributes rttrs) {
		log.trace("registerProcess({}, {}, {}, {}, {}) invoked.", member, title2, writer, content, currPage);

		// ------
		if(member == null || member.getId() == null) {
			return "redirect:/security/login";
		} // if
		
		// ------
		if(currPage == null) currPage = 1;

		// ------
		Board transientBoard = new Board();
		
		transientBoard.setTitle(title2);
		transientBoard.setWriter(writer);
		transientBoard.setContent(content);

		// ------
		Boolean isCreated = this.boardService.createBoard(transientBoard);
		log.info("  + isCreated({}), board({})", isCreated, transientBoard);

		// ------
		// (1) rtts.addFlashAttribute(key, val) 	=> Set attribute to the `Request Scope` as a shared attribute.
		// ------
		rttrs.addFlashAttribute("isCreated", isCreated);											// (1)
		rttrs.addFlashAttribute("seq", transientBoard.getSeq());								// (1)
		rttrs.addFlashAttribute(SharedAttributes.CURRENT_PAGE, currPage);		// (1)

		// ------
		// (2) rtts.addAttribute(key, val) 			=> Set attribute to the request parameter with GET method in the `Query String`.
		// ------
		rttrs.addAttribute("isCreated", isCreated);													// (2)
		rttrs.addAttribute("seq", transientBoard.getSeq());										// (2)
		rttrs.addAttribute(SharedAttributes.CURRENT_PAGE, currPage);				// (2)

		// -----
		return "redirect:getBoardList";				// OK, Re-direct with GET method
//		return "forward:getBoardList";				// OK, Forward with POST method
//		return "board/getBoardList";				// XX, Forward with BaseURI & POST method (Default)
	} // registerProcess
	
		
	@GetMapping("/registerWithDTO")
	String registerWithDTO(@ModelAttribute(SharedAttributes.CREDENTIAL) Member member) {
		log.trace("registerWithDTO({}) invoked.", member);

		// ------
		if(member == null || member.getId() == null) {
			return "redirect:/security/login";
		} else {
			return "board/registerWithDTO";
		} // if-else
	} // registerWithDTO
	
	@PostMapping(path="/registerWithDTO", params = { "title", "writer", "content" })
//	String registerProcessWithDTO(BoardDTO dto, RedirectAttributes rttrs) {
	String registerProcessWithDTO(
		@ModelAttribute(SharedAttributes.CREDENTIAL) Member member,
		Board dto, Integer currPage, RedirectAttributes rttrs) {
		log.trace("registerProcessWithDTO({}, {}, {}, {}) invoked.", member, dto, currPage, rttrs);

		// ------
		if(member == null || member.getId() == null) {
			return "redirect:/security/login";
		} // if
		
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
		// ------
		rttrs.addFlashAttribute("isCreated", isCreated);											// (1)
		rttrs.addFlashAttribute("seq", transientBoard.getSeq());								// (1)
		rttrs.addFlashAttribute(SharedAttributes.CURRENT_PAGE, currPage);		// (1)

		// ------
		// (2) rtts.addAttribute(key, val) 			=> Set attribute to the request parameter with GET method in the `Query String`.
		// ------
		rttrs.addAttribute("isCreated", isCreated);													// (2)
		rttrs.addAttribute("seq", transientBoard.getSeq());										// (2)
		rttrs.addAttribute(SharedAttributes.CURRENT_PAGE, currPage);				// (2)

		// -----
		return "redirect:getBoardList";				// OK, Re-direct with GET method
//		return "forward:getBoardList";				// OK, Forward with POST method
//		return "board/getBoardList";				// XX, Forward with BaseURI & POST method (Default)
	} // registerProcessWithDTO
	
		
	@GetMapping("/getBoard")
	String getBoard(@ModelAttribute(SharedAttributes.CREDENTIAL) Member member, Long seq, Model model) {
		log.trace("getBoard({}, {}, {}) invoked.", member, seq, model);

		// -----
		if(member == null || member.getId() == null) {
			return "redirect:/security/login";
		} // if
		
		// -----
		Board foundBoard = this.boardService.findBoardById(seq);
		Objects.requireNonNull(foundBoard);
		
		model.addAttribute(SharedAttributes.BOARD, foundBoard);
		
		// -----
		foundBoard.setCnt( foundBoard.getCnt() + 1 );
		this.boardService.modifyBoard(foundBoard);
		
		return "board/getBoard";
	} // getBoard
	
		
	@PostMapping(path="/update", params = { "seq", "title", "writer", "content" })
	String updateProcess(
		@ModelAttribute(SharedAttributes.CREDENTIAL) Member member,
		Board dto, Integer currPage, RedirectAttributes rttrs) {
		log.trace("updateProcess({}, {}, {}, {}) invoked.", member, dto, currPage, rttrs);

		// -----
		if(member == null || member.getId() == null) {
			return "redirect:/security/login";
		} // if
		
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
		rttrs.addAttribute(SharedAttributes.CURRENT_PAGE, currPage);

		// -----
		return "redirect:getBoardList";				// OK, Re-direct with GET method
//		return "forward:getBoardList";				// OK, Forward with POST method
//		return "board/getBoardList";				// XX, Forward with BaseURI & POST method (Default)
	} // updateProcess
	
		
	@RequestMapping(path="/delete", params = { "seq" }, method = {})
	String deleteProcess(
		@ModelAttribute(SharedAttributes.CREDENTIAL) Member member,
		@RequestParam("seq") Long seq, Integer currPage, RedirectAttributes rttrs) {
		log.trace("deleteProcess({}, {}, {}, {}) invoked.", member, seq, currPage, rttrs);

		// -----
		if(member == null || member.getId() == null) {
			return "redirect:/security/login";
		} // if
		
		// -----
		Board foundBoard = this.boardService.findBoardById(seq);
		
		if(foundBoard != null) this.boardService.removeBoard(seq);
		else log.info("  + *Not Found.");

		// -----
		if(currPage == null) currPage = 1;
		rttrs.addAttribute(SharedAttributes.CURRENT_PAGE, currPage);

		// -----
		return "redirect:getBoardList";				// OK, Re-direct with GET method
//		return "forward:getBoardList";				// OK, Forward with POST method
//		return "board/getBoardList";				// XX, Forward with BaseURI & POST method (Default)
	} // deleteProcess
	
	
	
} // end class

