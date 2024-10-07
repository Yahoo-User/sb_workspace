package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.persistence.BoardRepository;


@Log4j2
@NoArgsConstructor

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;


    @Transactional
    public void testTransaction() { // DataIntegrityViolationException
        log.trace("testTransaction() invoked.");
        log.info("\t+ this.boardRepository type: {}", this.boardRepository.getClass().getName());

        Board foundBoard = this.boardRepository.findById(2L).orElseThrow();
        foundBoard.setWriter("Pyramide");

//        ---
        Board board = new Board();
        board.setTitle("New Title2.");
        board.setContent("New Content2.");
//        board.setWriter(null);
        board.setWriter("Trinity2");

        this.boardRepository.save(board);

//        ---
        throw new DataIntegrityViolationException("Test");
    } // testTransaction

} // end class
