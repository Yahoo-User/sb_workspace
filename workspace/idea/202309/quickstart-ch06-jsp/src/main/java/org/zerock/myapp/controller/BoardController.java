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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.service.BoardService;

import java.util.List;
import java.util.Objects;


@Log4j2
@NoArgsConstructor

@SessionAttributes("key")
@Controller
public class BoardController implements InitializingBean, DisposableBean {
    @Setter(onMethod_={ @Autowired })
    private BoardService boardService;


    @GetMapping("/getBoardList")
    String getBoardList(Model model) {
        log.trace("getBoardList({}) invoked.", model);

/*
        // 1. Before creating `BoardService`
        List<Board> boardList = new ArrayList<>();

        for(int i=1; i<=10; i++) {
            Board board = new Board();

            board.setSeq(Long.valueOf(i));
            board.setTitle("TITLE_"+i);
            board.setWriter("Yoseph");
            board.setContent("CONTENT_"+i);
            board.setCreateDate(new Date());
            board.setCnt(0L);

            boardList.add(board);
        } // for
*/

        // 2. After creating `BoardService`
        List<Board> boardList = this.boardService.getBoardList();

        model.addAttribute("boardList", boardList);

        return "getBoardList";
    } // getBoardList

    @GetMapping("/insertBoard")
    void insertBoardView() {
        log.trace("insertBoardView() invoked.");

    } // insertBoardView

    @PostMapping("/insertBoard")
    String insertBoard(Board board) {
        log.trace("insertBoard({}) invoked.", board);

        this.boardService.insertBoard(board);

        return "redirect:getBoardList";
    } // insertBoard

    @GetMapping("/getBoard")
    String getBoard(Board board, Model model) {
        log.trace("getBoard({}, {}) invoked.", board, model);

        model.addAttribute("board", this.boardService.getBoard(board));

        return "getBoard";
    } // getBoard

    @PostMapping("/updateBoard")
    String updateBoard(Board board) {
        log.trace("updateBoard({}) invoked.", board);

        this.boardService.updateBoard(board);

        return "redirect:getBoardList";
    } // updateBoard

    @GetMapping("/deleteBoard")
    String deleteBoard(Board board) {
        log.trace("deleteBoard({}) invoked.", board);

        this.boardService.deleteBoard(board);

        return "redirect:getBoardList";
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
