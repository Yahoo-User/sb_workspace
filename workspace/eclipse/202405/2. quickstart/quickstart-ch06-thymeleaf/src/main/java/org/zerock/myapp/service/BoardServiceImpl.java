package org.zerock.myapp.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.myapp.common.CommonBeanCallbacks;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.persistence.BoardRepository;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service("boardService")
public class BoardServiceImpl
	extends CommonBeanCallbacks implements BoardService {
	@Autowired(required = true) private BoardRepository boardRepo;
	
	
	@Override
	public void postConstruct() {
		log.trace("(2) postConstruct( {} ) invoked.", this.beanName);
		log.info("  + this.boardRepo: {}, type: {}", this.boardRepo, this.boardRepo.getClass().getName());
	} // postConstruct
	
	
	@Override
	public
	Page<Board> findAllBoards(Integer currPage) {
		log.trace("findAllBoards({}) invoked.", currPage);
		
		// -------
		int linesPerPage = 20;
		Pageable paging = PageRequest.of(--currPage, linesPerPage, Sort.Direction.DESC, "seq");

		// -------
		return this.boardRepo.findAll(paging);
	} // findAllBoards
	
	@Override
	public
	boolean createBoard(Board board) {
		log.trace("createBoard({}) invoked.", board);
		
		Objects.requireNonNull(board);
		return this.boardRepo.save(board) != null ? true : false;
	} // createBoard
	
	@Override
	public
	Board findBoardById(Long seq) {
		log.trace("findBoardById({}) invoked.", seq);
		
		Objects.requireNonNull(seq);
		return this.boardRepo.findById(seq).orElse(null);
	} // findBoardById
	
	@Override
	public
	boolean modifyBoard(Board board) {
		log.trace("modifyBoard({}) invoked.", board);
		
		Objects.requireNonNull(board);
		return this.boardRepo.save(board) != null ? true : false;
	} // modifyBoard
	
	@Override
	public
	void removeBoard(Long seq) {
		log.trace("removeBoard({}) invoked.", seq);
		
		Objects.requireNonNull(seq);
		this.boardRepo.deleteById(seq);
	} // removeBoard
	
} // end interface


