package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Board;


public interface BoardService {

	public abstract List<Board> getBoardList();
	public abstract void insertBoard(Board board);
	public abstract Board getBoard(Long seq);
	public abstract void updateBoard(Board board);
	public abstract void deleteBoard(Long seq);

} // end interface