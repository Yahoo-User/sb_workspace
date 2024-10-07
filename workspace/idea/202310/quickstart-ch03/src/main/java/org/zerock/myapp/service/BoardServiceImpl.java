package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BoardVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Log4j2
@NoArgsConstructor

@Service
public class BoardServiceImpl implements BoardService {


    @Override
    public String hello(String name) {
        log.trace("hello({}) invoked.", name);

        return "Hello, " + name;
    } // hello

    @Override
    public BoardVO getBoard() {
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

    @Override
    public List<BoardVO> getBoardList() {
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
