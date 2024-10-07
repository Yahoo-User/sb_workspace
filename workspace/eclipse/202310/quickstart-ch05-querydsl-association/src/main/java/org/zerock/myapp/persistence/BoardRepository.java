package org.zerock.myapp.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.myapp.domain.Board;



public interface BoardRepository extends CrudRepository<Board, Long> {

	public abstract List<Board> findBoardByTitle(String title);
//	public abstract List<Board> findByWriter(String writer);
	
//	public abstract List<Board> findByTitleContainingAndWriterContaining(String title, String writer);		// title LIKE !? AND writer LIKE !?
	public abstract List<Board> findByContentContaining(String content);									// Like '%'||?1||'%'
	public abstract List<Board> findByTitleContainingOrContentContaining(String title, String content);		// title LIKE !? OR content LIKE !?
	public abstract List<Board> findByTitleContainingOrderBySeqDesc(String title);							// title LIKE !? ORDER BY seq DESC
	
//	public abstract List<Board> findByTitleContaining(String title, Pageable paging);						// title LIKE !? and Paging
	public abstract Page<Board> findByTitleContaining(String title, Pageable paging);						// title LILE !? and Paging, return Page<T>
	
	
//	================================ //
//	By using JPQL
//	================================ //
	
	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")		// ?1 -> Location-based Parameter
	List<Board> queryAnnotation1(String keyword);
	
//	@Query("""
//			SELECT b.seq, b.title, b.writer, b.createDate, b.cnt FROM Board b
//			WHERE b.title LIKE %:keyword%
//			ORDER BY b.seq DESC
//			""")																	// :keyword -> Name-based Parameter
//	List<Object[]> queryAnnotation2(@Param("keyword") String keyword);
	
	
	@Query("SELECT b FROM Board b ORDER BY b.seq DESC")
	List<Board> queryAnnotation4(Pageable paging);

	
//	================================ //
//	By using Native Query
//	================================ //
	
//	@Query(
//		value = "SELECT seq, title, writer, create_date FROM board WHERE title LIKE '%'||?||'%' ORDER BY seq DESC",
//		nativeQuery = true
//	)
//	List<Object[]> queryAnnotation3(String keyword);
	
} // end interface
