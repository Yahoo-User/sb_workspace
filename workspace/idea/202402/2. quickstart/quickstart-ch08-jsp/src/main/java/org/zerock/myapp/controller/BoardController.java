package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.security.SecurityUser;
import org.zerock.myapp.service.BoardService;
import org.zerock.myapp.service.MemberService;

import java.util.Objects;


@Log4j2
@NoArgsConstructor

@RequestMapping("/board/*")
@Controller
public class BoardController implements InitializingBean {
    @Setter(onMethod_ = @Autowired)
    private BoardService boardService;
    @Setter(onMethod_ = @Autowired)
    private MemberService memberService;


    @GetMapping("getBoardList")
    void getBoardList(@ModelAttribute("cri") Criteria cri, Model model) throws ControllerException {
        log.trace("getBoardList({}, {}) invoked.", cri, model);

        try {
            Pageable pageable = PageRequest.of(cri.getPageNumber() - 1, cri.getPageSize(), Sort.Direction.DESC, "seq");
            Page<Board> boardPage = this.boardService.getBoardList(pageable);

            // =================================================================
            // Page<T> Useful Methods for Pagination.
            // =================================================================
//            log.info("\t01. totalPages: {}", boardPage.getTotalPages());                        // 총페이지수
//            log.info("\t02. totalElements: {}", boardPage.getTotalElements());                  // 총레코드수
//            log.info("\t03. isFirst: {}", boardPage.isFirst());                                 // 첫페이지여부
//            log.info("\t04. isLast: {}", boardPage.isLast());                                   // 마지막페이지여부
//            log.info("\t05. isEmpty: {}", boardPage.isEmpty());                                 // 현재빈페이지여부
//            log.info("\t06. numberOfElements: {}", boardPage.getNumberOfElements());            // 현재페이지요소개수
//            log.info("\t07. hasNext: {}", boardPage.hasNext());                                 // 다음페이지존재여부
//            log.info("\t08. hasPrevious: {}", boardPage.hasPrevious());                         // 이전페이지존재여부
//            log.info("\t09. hasContent: {}", boardPage.hasContent());                           // 현재내용존재여부
//            log.info("\t10. pageable: {}", boardPage.getPageable());                            // 현재페이지정보획득
//            log.info("\t11. nextPageable: {}", boardPage.nextPageable());                       // 다음페이지정보획득
//            log.info("\t12. nextOrLastPageable: {}", boardPage.nextOrLastPageable());           // 다음또는마지막페이지정보획득(*)
//            log.info("\t13. previousPageable: {}", boardPage.previousPageable());               // 이전페이지정보획득
//            log.info("\t14. previousOrFirstPageable: {}", boardPage.previousOrFirstPageable()); // 이전또는첫페이지정보획득(*)
            // =================================================================

            cri.initialize(boardPage);
            log.info("\t+ cri: {}", cri);

            Objects.requireNonNull(boardPage);
            model.addAttribute("boardPage", boardPage);
        } catch(Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // getBoardList

    @GetMapping(path = "getBoard", params = { "seq", "pageNumber" })
    void getBoard(Criteria cri, Board board, Model model) throws ControllerException {
        log.trace("getBoard({}, {}, {}) invoked.", cri, board, model);

        try {
            Board foundBoard = this.boardService.select(board);

            Objects.requireNonNull(foundBoard);
            model.addAttribute("board", foundBoard);
        } catch(Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // getBoard

    @GetMapping("insertBoard")
    void insertBoardView() {} // View-Controller

    // ----------------------------------------- //
    // *** 1. Before applying Spring Security ***
    // ----------------------------------------- //
//    @PostMapping(path = "insertBoard", params = { "title", "content", "writer" })
//    String insertBoard(Board board, String writer) throws ControllerException {
//        log.trace("insertBoard({}, {}) invoked.", board, writer);
//
//        try {
//            Objects.requireNonNull(writer);
//            Member member = new Member();
//            member.setId(writer);
//
//            member = this.memberService.select(member);
//            log.info("\t+ member: {}", member);
//
//            Objects.requireNonNull(member);
//            board.setMember(member);
//            log.info("\t+ board: {}", board);
//
//            Integer affectedRows = this.boardService.insert(board);
//            log.info("\t+ affectedRows: {}", affectedRows);
//
//            return "redirect:getBoardList";
//        } catch(Exception e) {
//            throw new ControllerException(e);
//        } // try-catch
//    } // insertBoard

    // ----------------------------------------- //
    // *** 2. After applying Spring Security ***
    // ----------------------------------------- //
    @PostMapping(path = "insertBoard", params = { "title", "content" })
    String insertBoard(Board board, @AuthenticationPrincipal SecurityUser principal) throws ControllerException {
        log.trace("insertBoard({}, {}) invoked.", board, principal);

        try {
            Objects.requireNonNull(principal);
            board.setMember(principal.getMember());
            log.info("\t+ board: {}", board);

            Integer affectedRows = this.boardService.insert(board);
            log.info("\t+ affectedRows: {}", affectedRows);

            return "redirect:getBoardList";
        } catch(Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // insertBoard

    @PostMapping(path="updateBoard", params = { "seq", "cnt", "title", "content", "pageNumber" })
    String updateBoard(Board board, String pageNumber, RedirectAttributes rttrs) throws ControllerException {
        log.trace("updateBoard({}, {}, {}) invoked.", board, pageNumber, rttrs);

        try {
            Integer affectedRows = this.boardService.update(board);
            log.info("\t+ affectedRows: {}", affectedRows);

            rttrs.addAttribute("pageNumber", pageNumber);
            return "redirect:getBoardList";
        } catch(Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // updateBoard

    @GetMapping(path="deleteBoard", params = { "seq", "pageNumber" })
    String deleteBoard(Board board, String pageNumber, RedirectAttributes rttrs) throws ControllerException {
        log.trace("deleteBoard({}, {}, {}) invoked.", board, pageNumber, rttrs);

        try {
            Integer affectedRows = this.boardService.delete(board);
            log.info("\t+ affectedRows: {}", affectedRows);

            rttrs.addAttribute("pageNumber", pageNumber);
            return "redirect:getBoardList";
        } catch(Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // deleteBoard


    @Override
    public void afterPropertiesSet() {
        log.trace("afterPropertiesSet() invoked.");

        Objects.requireNonNull(this.boardService);
        Objects.requireNonNull(this.memberService);
    } // afterPropertiesSet

} // end class
