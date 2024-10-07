package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.persistence.BoardRepository;

import java.util.Objects;


@Log4j2
@NoArgsConstructor

@Service
public class BoardServiceImpl implements BoardService, InitializingBean {
    @Setter(onMethod_ = @Autowired)
    private BoardRepository boardRepository;


    @Override
    public Page<Board> getBoardList(Pageable pageable) throws ServiceException {
        log.trace("getBoardList({}) invoked.", pageable);

        try {
            Page<Board> currPage = this.boardRepository.getBoardList(pageable);
            log.info(">> Page<Board> : number({}), size({}), hasContent({}), numberOfElements({})",
                    currPage.getNumber(), currPage.getSize(), currPage.hasContent(), currPage.getNumberOfElements());

            return currPage;
        } catch(Exception e) {
            throw new ServiceException(e);
        } // try-catch
    } // getBoardList

    @Override
    public Integer insert(Board board) throws ServiceException {
        log.trace("insert({}) invoked.", board);

        try {
            this.boardRepository.save(board);

            return 1;
        } catch(Exception e) {
            throw new ServiceException(e);
        } // try-catch
    } // insert

    @Override
    public Board select(Board board) throws ServiceException {
        log.trace("select({}) invoked.", board);

        try {
            return this.boardRepository.findById(board.getSeq()).orElseThrow();
        } catch(Exception e) {
            throw new ServiceException(e);
        } // try-catch
    } // select

    @Override
    public Integer update(Board board) throws ServiceException {
        log.trace("update({}) invoked.", board);

        Board foundBoard = this.select(board);

        foundBoard.setTitle(board.getTitle());
        foundBoard.setContent(board.getContent());
        foundBoard.setCnt(board.getCnt());

        return this.insert(foundBoard);
    } // update

    @Override
    public Integer delete(Board board) throws ServiceException {
        log.trace("delete({}) invoked.", board);

        try {
            this.boardRepository.deleteById(board.getSeq());
            return 1;
        } catch(Exception e) {
            throw new ServiceException(e);
        } // try-catch
    } // delete


    @Override
    public void afterPropertiesSet() {
        log.trace("afterPropertiesSet() invoked.");

        Objects.requireNonNull(this.boardRepository);
        log.info("\t+ this.boardRepository: {}", this.boardRepository);
    } // afterPropertiesSet

} // end class
