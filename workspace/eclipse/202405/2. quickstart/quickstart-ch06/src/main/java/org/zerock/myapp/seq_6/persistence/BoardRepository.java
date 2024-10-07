package org.zerock.myapp.seq_6.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.myapp.seq_4.domain.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {
	// 1. Query Methods With Various Operators
	
} // end interface
