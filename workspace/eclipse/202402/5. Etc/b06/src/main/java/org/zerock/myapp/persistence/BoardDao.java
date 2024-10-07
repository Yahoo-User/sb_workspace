package org.zerock.myapp.persistence;

import org.springframework.data.repository.CrudRepository;
import org.zerock.myapp.domain.Board;


public interface BoardDao extends CrudRepository<Board, Long> {} // end interface
