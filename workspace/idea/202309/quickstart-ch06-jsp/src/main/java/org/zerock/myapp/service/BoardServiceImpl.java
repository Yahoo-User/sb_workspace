package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.persistence.BoardRepository;

import java.util.List;
import java.util.Objects;


@Log4j2
@NoArgsConstructor

@Service
public class BoardServiceImpl
        implements BoardService, InitializingBean, DisposableBean {

    @Setter(onMethod_= @Autowired)
    private BoardRepository boardRepository;


    @Override
    public List<Board> getBoardList() {
        log.trace("getBoardList() invoked.");

        return ( List<Board> ) this.boardRepository.findAll();
    } // getBoardList

    @Override
    public void insertBoard(Board board) {
        log.trace("insertBoard({}) invoked.", board);

        this.boardRepository.save(board);
    } // insertBoard

    @Override
    public Board getBoard(Board board) {
        log.trace("getBoard({}) invoked.", board);

        Board foundBoard = this.boardRepository.findById(board.getSeq()).get();
        Objects.requireNonNull(foundBoard);

        foundBoard.setCnt(foundBoard.getCnt() + 1);
        this.boardRepository.save(foundBoard);

        return foundBoard;
    } // getBoard

    @Override
    public void updateBoard(Board board) {
        log.trace("updateBoard({}) invoked.", board);

        Board foundBoard = this.boardRepository.findById(board.getSeq()).get();

        foundBoard.setTitle(board.getTitle());
        foundBoard.setContent(board.getContent());

        this.boardRepository.save(foundBoard);
    } // updateBoard

    @Override
    public void deleteBoard(Board board) {
        log.trace("deleteBoard({}) invoked.", board);

        this.boardRepository.deleteById(board.getSeq());
    } // deleteBoard

    @Override
    public void afterPropertiesSet() throws Exception {
        log.trace("afterPropertiesSet() invoked.");

        Objects.requireNonNull(this.boardRepository);
        log.info("\t+ this.boardRepository: {}", this.boardRepository);
    } // afterPropertiesSet

    @Override
    public void destroy() throws Exception {
        log.trace("destroy() invoked.");

    } // destroy

} // end class
