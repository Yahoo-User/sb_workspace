package org.zerock.myapp.seq_8.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.myapp.seq_6.domain.Board;


// Automatically implemented by Spring Boot with auto-proxy techniques.
//						-------- <Repository> --------
//						|												|
//			<CrudRepository>				<PagingAndSortingRepository>
//						|												|
//			<ListCrudRepository>		<ListPagingAndSortingRepository>
//						|												|
//						------  <JpaRepository>  -----
//
//	(1) Repository												- Spring Data
//	(2) CrudRepository										- Spring Data
//	(3) PagingAndSortingRepository				- Spring Data
//	(4) ListCrudRepository								- Spring Data
//	(5) ListPagingAndSortingRepository			- Spring Data
//	(6) JpaRepository										- Spring Data *JPA

public interface BoardRepository extends JpaRepository<Board, Long> {} // end interface


