package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.service.BoardService;

import java.util.List;
import java.util.Objects;


@Log4j2
@NoArgsConstructor

@SessionAttributes("member")
@Controller
public class BoardController implements InitializingBean, DisposableBean {
    @Setter(onMethod_={ @Autowired })
    private BoardService boardService;


    @ModelAttribute("member")
    Member setMember() {
        log.trace("setMember() invoked.");

        return new Member();
    } // setMember

    @GetMapping("/getBoardList")
    String getBoardList(@ModelAttribute("member") Member member, Model model) {
        log.trace("getBoardList({}, {}) invoked.", member, model);

        if(member.getId() == null) {
            return "redirect:login";
        } // if

        List<Board> boardList = this.boardService.getBoardList();
        model.addAttribute("boardList", boardList);

        return "getBoardList";
    } // getBoardList

    @GetMapping("/insertBoard")
    String insertBoardView(@ModelAttribute("member") Member member) {
        log.trace("insertBoardView({}) invoked.", member);

        if(member.getId() == null) {
            return "redirect:login";
        } // if

        return "insertBoard";
    } // insertBoardView

    @PostMapping("/insertBoard")
    String insertBoard(@ModelAttribute("member") Member member, Board board) {
        log.trace("insertBoard({}, {}) invoked.", member, board);

        if(member.getId() == null) {
            return "redirect:login";
        } // if

        this.boardService.insertBoard(board);

        return "redirect:getBoardList";           // OK
//        return "forward:getBoardList";            // XX : 405 Not Supported.
    } // insertBoard

    @GetMapping("/getBoard")
    String getBoard(@ModelAttribute("member") Member member, Board board, Model model) {
        log.trace("getBoard({}, {}, {}) invoked.", member, board, model);

        if(member.getId() == null) {
            return "redirect:login";
        } // if

        model.addAttribute("board", this.boardService.getBoard(board));
        return "getBoard";
    } // getBoard

    @PostMapping("/updateBoard")
    String updateBoard(@ModelAttribute("member") Member member, Board board) {
        log.trace("updateBoard({}, {}) invoked.", member, board);

        if(member.getId() == null) {
            return "redirect:login";
        } // if

        this.boardService.updateBoard(board);

        return "redirect:getBoardList";
//        return "forward:getBoardList";      // XX
    } // updateBoard

    @GetMapping("/deleteBoard")
    String deleteBoard(@ModelAttribute("member") Member member, Board board) {
        log.trace("deleteBoard({}, {}) invoked.", member, board);

        if(member.getId() == null) {
            return "redirect:login";
        } // if

        this.boardService.deleteBoard(board);

//        return "redirect:getBoardList";       // OK
        return "forward:getBoardList";          // OK
    } // deleteBoard



    @Override
    public void afterPropertiesSet() throws Exception {
        log.trace("afterPropertiesSet() invoked.");

        Objects.requireNonNull(this.boardService);
        log.info("\t+ this.boardService: {}", this.boardService);
    } // afterPropertiesSet

    @Override
    public void destroy() throws Exception {
        log.trace("destroy() invoked.");

    } // destroy

} // end class
