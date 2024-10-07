package org.zerock.myapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.exception.ServiceException;


// Board : Entity Class
public interface BoardService {
    Page<Board> getBoardList(Pageable pageable) throws ServiceException;

    Integer     insert(Board board) throws ServiceException;
    Integer     update(Board board) throws ServiceException;
    Integer     delete(Board board) throws ServiceException;
    Board       select(Board board) throws ServiceException;
} // end interface
