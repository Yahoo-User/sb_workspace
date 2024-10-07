package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.BoardVO;


public interface BoardService {
	
	
	public abstract String hello(String name, Integer age);
	public abstract BoardVO getBoard();
	public abstract List<BoardVO> getBoardList();

} // end interface
