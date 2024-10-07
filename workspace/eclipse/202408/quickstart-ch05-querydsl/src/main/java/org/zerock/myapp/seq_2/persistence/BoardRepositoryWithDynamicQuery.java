package org.zerock.myapp.seq_2.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.myapp.seq_1.domain.Board;


/**
 * Important :
 * 	(1) Custom Repository using Querydsl should additionally extend "QuerydslPredicateExecutor" interface of Spring Data
 * 		  with `Repository` interface of Spring Data JPA.	(***)
 * (2) Also, focus on the child "ListQuerydslPredicateExecutor" interface of "QuerydslPredicateExecutor" interface.
 */
public interface BoardRepositoryWithDynamicQuery extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {

} // end interface


