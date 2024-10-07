package org.zerock.myapp.persistence;

import org.springframework.data.repository.CrudRepository;
import org.zerock.myapp.domain.Board;


public interface BoardRepository extends CrudRepository<Board, Long> {

} // end class
