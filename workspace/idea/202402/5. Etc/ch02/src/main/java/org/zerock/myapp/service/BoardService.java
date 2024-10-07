package org.zerock.myapp.service;

import org.zerock.myapp.domain.BoardVO;
import java.util.List;


public interface BoardService {

    public abstract String hello(String name);
    public abstract BoardVO getBoard();
    public abstract List<BoardVO> getBoardList();

} // end interface
