package org.zerock.myapp.seq_7.service;

import org.springframework.data.domain.Page;
import org.zerock.myapp.seq_4.domain.Board;


public interface BoardService {

	public abstract 
	Page<Board> findAllBoards(Integer currPage);
	
	public abstract
	boolean createBoard(Board board);
	
	public abstract
	Board findBoardById(Long seq);
	
	public abstract
	boolean modifyBoard(Board board);
	
	public abstract
	void removeBoard(Long seq);

} // end interface

