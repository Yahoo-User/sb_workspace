package org.zerock.myapp.seq_11.persistence;

import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.myapp.seq_6.domain.Board;


// Declares Spring Data JPA *Query *Methods.
public interface BoardRepositoryWithQueryMethods extends JpaRepository<Board, Long> {
	
	/**
	 * The return types of a Query Method could be possible as the following types:
	 * 		(1) List<T>		(2) Page<T>		(3) Slice<T>
	 * 
	 * All types like the above are the sub type of Collection<T> type.
	*/

	public abstract List<Board> findBoardBySeq(Long seq);						// Full
	public abstract List<Board> findBoardByTitle(String title);					// Full
	public abstract List<Board> findBoardByWriter(String writer);			// Full
	public abstract List<Board> findBoardByContent(String content);		// Full
	public abstract List<Board> findBoardByCnt(int cnt);							// Full
	
	public abstract Slice<Board> findBySeq(Long seq);								// Short-Hand
	public abstract Slice<Board> findByTitle(String title);							// Short-Hand
	public abstract Slice<Board> findByWriter(String writer);					// Short-Hand
	public abstract Slice<Board> findByContent(String content);				// Short-Hand
	public abstract Slice<Board> findByCnt(int cnt);									// Short-Hand

} // end interface
