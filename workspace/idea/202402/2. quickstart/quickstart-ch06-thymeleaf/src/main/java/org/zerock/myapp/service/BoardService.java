package org.zerock.myapp.service;

import org.zerock.myapp.domain.Board;

import java.util.List;


public interface BoardService {
    public abstract List<Board> getBoardList();
    public abstract void insertBoard(Board board);
    public abstract Board getBoard(Board board);
    public abstract void updateBoard(Board board);
    public abstract void deleteBoard(Board board);
} // end interface
