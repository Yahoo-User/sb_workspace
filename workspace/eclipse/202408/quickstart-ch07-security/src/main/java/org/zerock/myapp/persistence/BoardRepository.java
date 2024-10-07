package org.zerock.myapp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.myapp.domain.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {
	// 1. Query Methods With Various Operators
	
} // end interface
