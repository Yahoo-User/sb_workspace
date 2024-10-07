package org.zerock.myapp.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.persistence.BoardDao;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service
public class BoardServiceImpl
	implements BoardService, InitializingBean {
	
	@Autowired
	private BoardDao dao;
	
	
	@Override
	public List<Board> getBoardList() {
		log.debug("getBoardList() invoked.");
		
		Iterable<Board> iterable = this.dao.findAll();
		return (List<Board>) iterable;
	} // getBoardList
	
	@Override
	public void insertBoard(Board board) {
		log.debug("insertBoard({}) invoked.", board);
		
		this.dao.save(board);
	} // insertBoard
	
	@Override
	public Board getBoard(Long seq) throws NoSuchElementException {
		log.debug("getBoard({}) invoked.", seq);
		
		return this.dao.findById(seq).get();
	} // getBoard
	
	@Override
	public void updateBoard(Board board) {
		log.debug("updateBoard({}) invoked.", board);
		
		this.dao.save(board);
	} // updateBoard
	
	@Override
	public void deleteBoard(Long seq) {
		log.debug("deleteBoard({}) invoked.", seq);
		
		this.dao.deleteById(seq);
	} // deleteBoard

	
//	------------------------------ 
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
		
		assert this.dao != null;
		log.info("\t+ dao: {}, type: {}", this.dao, this.dao.getClass().getName());
	} // afterPropertiesSet

} // end class
