package org.zerock.myapp.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.zerock.myapp.domain.Board;


public interface DynamicBoardRepository
        extends CrudRepository<Board, Long>,
                QuerydslPredicateExecutor<Board> {

} // end interface
