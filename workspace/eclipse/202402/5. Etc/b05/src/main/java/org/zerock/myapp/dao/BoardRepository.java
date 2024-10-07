package org.zerock.myapp.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.zerock.myapp.domain.Board;


public interface BoardRepository extends CrudRepository<Board, Long> {

//	---------------------------------------
//	WHERE
//    	title = ?
//	---------------------------------------
	List<Board> findByTitle(String title);

	
//	---------------------------------------
//	WHERE
//    	title LIKE '%'||?||'%' ESCAPE ?
//	---------------------------------------
	List<Board> findByTitleContaining(String title);

	
//	---------------------------------------
//	WHERE
//    	title LIKE ? ESCAPE ?
//	---------------------------------------
	List<Board> findByTitleLike(String title);
	

//	---------------------------------------
//	 WHERE
//    	title   LIKE '%'||?||'%' ESCAPE ? OR 
//		content LIKE '%'||?||'%' ESCAPE ?
//	---------------------------------------
	List<Board> findByTitleContainingOrContentContaining(String title, String content);		// OK

	
//	---------------------------------------
//	 java.lang.IllegalStateException:
//	 	Method expects at least 2 arguments but only found 1.
//	    This leaves an operator of type CONTAINING for property content unbound.
//	---------------------------------------
//	List<Board> findByTitleContainingOrContentContaining(String searchKeyword);				// XX

	
//	---------------------------------------
//	WHERE
//    	title LIKE '%'||?||'%' ESCAPE ? 
//  ORDER BY
//    	seq DESC
//	---------------------------------------
	List<Board> findByTitleContainingOrderBySeqDesc(String title);
	
	
//	---------------------------------------
//	WHERE
//    	title LIKE '%'||?||'%' ESCAPE ? 
//  ORDER BY
//      seq DESC,
//      insert_ts DESC
//	LIMIT ? OFFSET ?
//	---------------------------------------
	List<Board> findByTitleContaining(String title, Pageable paging);
	
	
	Page<Board> findByContentContaining(String title, Pageable paging);
	
	
//	======================================= //
//	Query Annotation
//	======================================= //
	
	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(String searchKeyword);

	@Query("SELECT b FROM Board b WHERE b.content LIKE %:searchContent% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest2(@Param("searchContent") String searchKeyword);

//	@Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")
//	List<Object[]> queryAnnotationTest3(@Param("searchTitle") String searchKeyword);
	
//	@Query(value="SELECT * FROM board WHERE writer LIKE %:searchWriter% ORDER BY seq DESC", nativeQuery=true)
//	List<Object[]> queryAnnotationTest4(@Param("searchWriter") String searchKeyword);

	@Query("SELECT b FROM Board b")
//	@Query("SELECT b FROM Board b ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest5(Pageable paging);
	
} // end interface
