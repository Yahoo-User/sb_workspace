package org.zerock.myapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BoardVOWithLombokValue;
import org.zerock.myapp.domain.BoardVOWithRecordType;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service
public class BoardServiceImpl implements BoardService {
	
	@Override
	public String hello(String name) {
		log.trace("hello({}) invoked.", name);
		return "Hello, %s".formatted(name);
	} // hello

	@Override
	public BoardVOWithRecordType getBoard() {
		log.trace("getBoard() invoked.");
//		return new BoardVOWithRecordType();			// OK
		return new BoardVOWithRecordType(1, "title_1", "writer_1", "content_1", LocalDateTime.now(), 1);
	} // getBoard

	@Override
	public BoardVOWithLombokValue getBoard2() {
		log.trace("getBoard2() invoked.");
		return new BoardVOWithLombokValue(2, "title_2", "writer_2", "content_2", LocalDateTime.now(), 2);
	} // getBoard2

	@Override
	public List<BoardVOWithRecordType> getBoardList() {
		log.trace("getBoardList() invoked.");
		
		List<BoardVOWithRecordType> list = new ArrayList<>();

		// for (int i = startInclusive; i < endExclusive ; i++) { ... }
		IntStream.range(1, 11).forEachOrdered(i -> {
			log.info("forEachOrdered({}) invoked.", i);
			BoardVOWithRecordType vo = new BoardVOWithRecordType(i, "title_"+i, "writer_"+i, "content_"+i, LocalDateTime.now(), 0);
			list.add(vo);
		});	// .forEachOrdered
		
		return list;
	} // getBoardList

	@Override
	public List<BoardVOWithLombokValue> getBoardList2() {
		log.trace("getBoardList2() invoked.");
		
		List<BoardVOWithLombokValue> list = new ArrayList<>();
		
		// for (int i = startInclusive; i <= endInclusive ; i++) { ... }
		IntStream.rangeClosed(1, 10).forEachOrdered(i -> {
			log.info("forEachOrdered({}) invoked.", i);
			BoardVOWithLombokValue vo = new BoardVOWithLombokValue(i, "title_"+i, "writer_"+i, "content_"+i, LocalDateTime.now(), 0);
			list.add(vo);
		});	// .forEachOrdered
		
		return list;
	} // getBoardList2

} // end class
