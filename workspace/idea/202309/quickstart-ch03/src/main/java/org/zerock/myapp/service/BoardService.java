package org.zerock.myapp.service;


import org.zerock.myapp.domain.BoardVO;

import java.util.List;


public interface BoardService {
    String hello(String name);
    BoardVO getBoard();
    List<BoardVO> getBoardList();
} // end interface
