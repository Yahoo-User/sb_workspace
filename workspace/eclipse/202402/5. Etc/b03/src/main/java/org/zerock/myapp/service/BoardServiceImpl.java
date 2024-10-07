package org.zerock.myapp.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BoardVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service
public class BoardServiceImpl implements BoardService {

	
	@Override
	public String hello(String name, Integer age) {
		log.info("hello({}, {}) invoked.", name, age);
		
		return String.format("name: %s, age: %d", name, age);
	} // hello

	@Override
	public BoardVO getBoard() {
		log.info("getBoard() invoked.");
		
		BoardVO board=new BoardVO();
		
		board.setSeq(1);
		board.setTitle("TITLE_1");
		board.setContent("CONTENT_1");
		board.setWriter("WRITER_1");
		board.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
		board.setSeq(100);
		
		return board;
	} // getBoard

	@Override
	public List<BoardVO> getBoardList() {
		List<BoardVO> list=new ArrayList<>();
		
		for(int i=0;i<10;i++) {
			BoardVO board=new BoardVO();
			
			board.setSeq(i);
			board.setTitle("TITLE_"+i);
			board.setContent("CONTENT_"+i);
			board.setWriter("WRITER_"+i);
			board.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
			board.setCnt(0);
			
			list.add(board);
		} // for
		
		return list;
	} // getBoardList

} // end class
