package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.BoardVOWithLombokValue;
import org.zerock.myapp.domain.BoardVOWithRecordType;


public interface BoardService {
	String hello(String name);
	BoardVOWithRecordType getBoard();
	BoardVOWithLombokValue getBoard2();
	List<BoardVOWithRecordType> getBoardList();
	List<BoardVOWithLombokValue> getBoardList2();
} // end class

