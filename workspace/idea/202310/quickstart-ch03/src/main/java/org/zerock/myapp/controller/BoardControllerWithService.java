package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.service.BoardService;

import java.util.List;
import java.util.Objects;


@Log4j2
@NoArgsConstructor

@RestController
public class BoardControllerWithService implements InitializingBean {
    @Autowired private BoardService boardService;


    @GetMapping("/hello2")
    String hello2(String name) {
        log.trace("hello2({}) invoked.", name);
        return this.boardService.hello(name);
    } // hello2

    @GetMapping("/getBoard2")
    BoardVO getBoard2() {
        log.trace("getBoard2() invoked.");
        return this.boardService.getBoard();
    } // getBoard2

    @GetMapping("/getBoardList2")
    List<BoardVO> getBoardList2() {
        log.trace("getBoardList2() invoked.");
        return this.boardService.getBoardList();
    } // getBoardList2


    @Override
    public void afterPropertiesSet() {
        log.trace("afterPropertiesSet() invoked.");

        Objects.requireNonNull(this.boardService);
        log.info("\t+ this.boardService: {}, type: {}",
                this.boardService, this.boardService.getClass().getName());
    } // afterPropertiesSet

} // end class
