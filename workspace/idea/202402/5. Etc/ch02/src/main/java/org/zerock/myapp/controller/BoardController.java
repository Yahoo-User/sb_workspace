package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.service.BoardService;

import java.util.List;


@Log4j2
@NoArgsConstructor

@RestController
public class BoardController {
    @Autowired private BoardService service;


    @GetMapping("/hello")
    String hello(String name) {
        log.trace("hello({}) invoked.", name);
        return this.service.hello(name);
    } // hello


    @GetMapping("/getBoard")
    BoardVO getBoard() {
        log.trace("getBoard() invoked.");
        return this.service.getBoard();
    } // getBoard


    @GetMapping("/getBoardList")
    List<BoardVO> getBoardList() {
        log.trace("getBoardList() invoked.");
        return this.service.getBoardList();
    } // getBoardList



} // end class
