package org.zerock.myapp.controller;

import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.BoardVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Log4j2
@NoArgsConstructor

@RestController
public class BoardController {


    @GetMapping("/hello")
    String hello(HttpSession session, String name) {
        log.trace("hello({}, {}) invoked.", session, name);

        String sessionId = session.getId();
        return "Hello, " + name + '('+ sessionId + ')';
    } // hello

    @GetMapping("/getBoard")
    BoardVO getBoard() {
        log.trace("getBoard() invoked.");

        BoardVO vo = new BoardVO();
        vo.setSeq(1);
        vo.setTitle("테스트 제목...");
        vo.setWriter("Yoseph");
        vo.setContent("테스트 내용입니다 ....");
        vo.setCreateDate(new Date());
        vo.setCnt(0);

        log.info("\t+ vo: {}", vo);

        return vo;
    } // getBoard

    @GetMapping("/getBoardList")
    List<BoardVO> getBoardList() {
        log.trace("getBoardList() invoked.");

        List<BoardVO> list = new ArrayList<>();

        for(int i=1; i<=10; i++) {
            BoardVO vo = new BoardVO();
            vo.setSeq(i);
            vo.setTitle("제목" + i);
            vo.setWriter("Yoseph");
            vo.setContent(i + "번 내용입니다.");
            vo.setCreateDate(new Date());
            vo.setCnt(0);

            list.add(vo);
        } // for

        return list;
    } // getBoardList


} // end class
