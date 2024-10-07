package org.zerock.myapp.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.myapp.domain.Board;


public interface BoardRepository extends CrudRepository<Board, Long> {

    @Query("SELECT b FROM Board b")
    Page<Board> getBoardList(Pageable pageable);
} // end interface
