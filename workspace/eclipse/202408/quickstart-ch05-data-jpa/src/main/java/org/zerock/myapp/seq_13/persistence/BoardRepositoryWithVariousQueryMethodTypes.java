package org.zerock.myapp.seq_13.persistence;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.myapp.seq_6.domain.Board;


// Declares various types of *Query *Methods of Spring Data JPA.
public interface BoardRepositoryWithVariousQueryMethodTypes extends JpaRepository<Board, Long> {

	// 01. 'Containing' Operators :
	//		Rule) findBy + <field> + Containing
	//		SQL)  WHERE field LIKE ? ESCAPE '\'
	public abstract
	List<Board> findByContentContaining(String content);
	
	// 02. 'Containing' & 'Or' Operators :
	//		Rule) findBy + <field1> + Containing + Or + <field1> + Containing
	//		SQL) WHERE field1 LIKE ? ESCAPE '\' OR field1 LIKE ? ESCAPE '\'
	public abstract
	Slice<Board> findByTitleContainingOrContentContaining(String title, String content);

	// 03. 'Containing' & 'OrderBy' + <field> + { 'Asc' | 'Desc' } Operators :
	//	  *Note) 'OrderBy' operator must Not be used more than *Once in a method name.
	//				  But multiple fields could be ordered by multiple { 'Desc' | 'Asc' } keyword of 'OrderBy' Operator.
	//		Rule) findBy + <field1> + Containing + OrderBy + <field2> + { 'Asc' | 'Desc' }
	//		SQL) WHERE field1 LIKE ? ESCAPE '\' ORDER BY field2 { ASC | DESC }
	public abstract
	Slice<Board> findByTitleContainingOrderBySeqDesc(String title);
	
	/**
	 * ------------------------------------------------------------
	 * (1) Paging & Sorting of Query Methods in the Spring Data JPA
	 * ------------------------------------------------------------
	 * All *Query Methods of the Spring Data JPA can have a *Last 'Pageable' interface type parameter :
	 * 
	 * 		Ex2) findByXXX(T1 t1, T2 t2, ..., Pageable paging)							// Paging & Sorting
	 * 
	 * ------------------------------------------------------------
	 * (2) <Pageable> interface <- implements - <PageRequest> class
	 * ------------------------------------------------------------
	 * 		int 
	 * 			currPage = 0, 				// starts with 0 (***)
	 * 			linesPerPage = 10;
	 * 
	 * 		Ex1) Pageable paging = PageRequest.of(currPage, linesPerPage);
	 * 		Ex2) Pageable paging = PageRequest.of(currPage, linesPerPage, Sort.Direction.DESC, "seq");
	 * 
	 * 		SQL)  OFFSET ? ROWS FETCH FIRST ? ROWS ONLY
	 * 
	 * ----------------------------------------------
	 * (3) How to convert LocalDateTime into Date ?
	 * ----------------------------------------------
	 * Target Date: 2024-05-12T22:16:28.590840
	 * 
	 * LocalDateTime criteriaLocalDateTime = LocalDateTime.of(2024, Month.MAY, 12, 22, 16, 28, 590840000);		
	 * long epochSeconds = criteriaLocalDateTime.toEpochSecond(ZoneOffset.UTC);
	 * Date criteriaDate = new Date(epochSeconds);
	 * ----------------------------------------------
	 */
	
	// 04. Only Paging
	//	  *Note) 'OrderBy' operator must Not be used more than *Once in a method name.
	//				  But multiple fields could be ordered by multiple { 'Desc' | 'Asc' } keyword of 'OrderBy' Operator.
	// 	  *Note) Paging query(= Return type: Page<T>) needs to have a Pageable parameter
	//	  Usage) PageRequest paging = PageRequest.of(currPage, linesPerPage);
	public abstract
	Page<Board> findByContentContainingOrderBySeqDesc(String content, Pageable paging);
	
	// 05. Paging with Sort
	// 	    *Note) Paging query(= Return type: Page<T>) needs to have a Pageable parameter
	//		Usage) PageRequest paging = PageRequest.of(currPage, linesPerPage, Sort.Direction.ASC | DESC, "<field to sort>");
	public abstract
	Page<Board> findByTitleContaining(String title, Pageable paging);
	
	// 06. 'And' Operator :
	//		Rule) findBy + <field1> + And + <field2>
	//		SQL) WHERE field1 = ?1 AND field2 = ?2
	public abstract
	List<Board> findByTitleAndContent(String title, String content);
	
	// 07. 'Or' Operator :
	//		Rule) findBy + <field1> + Or + <field2>
	//		SQL) WHERE field1 = ?1 OR field2 = ?2
	public abstract
	Slice<Board> findByTitleOrContent(String title, String content);
	
	// 08. 'Between' Operator :
	// 	  *Note) Paging query(= Return type: Page<T>) needs to have a Pageable parameter
	//		Rule) findBy + <field> + Between
	//		SQL) WHERE field BETWEEN ?1 AND ?2
	public abstract
	Page<Board> findBySeqBetween(long startSeq, long endSeq, Pageable paging);
	
	// 09. 'LessThan' Operator :
	// 		Rule) findBy + <field> + LessThan
	//		SQL) WHERE field < ?1
	public abstract
	Slice<Board> findBySeqLessThan(long criteriaSeq);
	
	// 10. 'LessThanEqual' Operator :
	//		Rule) findBy + <field> + LessThanEqual
	//		SQL)  WHERE field <= ?1
	public abstract
	List<Board> findByTitleLessThanEqual(String criteriaTitle);
	
	// 11. 'GreaterThan' Operator :
	// 		Rule) findBy + <field> + GreaterThan
	//		SQL)  WHERE field > ?1
	public abstract
	Slice<Board> findByContentGreaterThan(String criteriaContent);
	
	// 12. 'GreaterThanEqual' Operator :
	// 	  *Note) Paging query(= return type: Page<T>) needs to have a Pageable parameter
	//		Rule) findBy + <field> + GreaterThanEqual
	//		SQL) WHERE field >= ?1
	//				 OFFSET  ?2 ROWS FETCH FIRST ?3 ROWS ONLY
	public abstract
	Page<Board> findBySeqGreaterThanEqual(long criteriaSeq, Pageable paging);
	
	// 13. 'After' Operator :
	// 	  *Note) For LocalDateTime fields
	//		Rule) findBy + <Date Field> + After
	//		SQL)  WHERE datefield > ?1
	public abstract
	List<Board> findByCreateDateAfter(LocalDateTime criteriaCreateDate);
	
	// 14. 'Before' Operator :
	// 	  	*Note) Paging query(= Return type: Page<T>) needs to have a Pageable parameter
	// 		*Note) For LocalDateTime fields
	//		  Rule) findBy + <Date Field> + Before
	//		  SQL)  WHERE datefield < ?1
	public abstract
	Page<Board> findByCreateDateBefore(LocalDateTime criteriaCreateDate, Pageable paging);
	
	// 15. 'IsNull' Operator :
	//		Rule)  findBy + <field> + IsNull
	//		SQL)  WHERE field IS NULL
	public abstract
	Slice<Board> findByUpdateDateIsNull();
	
	// 16. 'IsNotNull' & 'NotNull' Operator :
	//		Rule)  findBy + <field> + { IsNotNull | NotNull }
	//		SQL)  WHERE field IS NOT NULL
	public abstract
	List<Board> findByCreateDateIsNotNull();
	
	public abstract
	List<Board> findByTitleNotNull();
	
	// 17. 'Like' Operator :
	//		Rule) findBy + <field> + Like
	//		SQL)  WHERE field LIKE ?1 ESCAPE '\'
	public abstract
	Slice<Board> findByTitleLike(String pattern);
	
	// 18. 'NotLike' Operator :
	//		Rule) findBy + <field> + NotLike
	//		SQL)  WHERE field NOT LIKE ?1 ESCAPE '\'
	public abstract
	List<Board> findByContentNotLike(String pattern);
	
	// 19. 'StartingWith' Operator :
	//		Rule) findBy + <field> + StartingWith
	//		SQL ) WHERE field LIKE ?1 ESCAPE '\'
	public abstract
	Slice<Board> findByWriterStartingWith(String startingWriter);
	
	// 20. 'EndingWith' Operator :
	//		Rule) findBy + <field> + EndingWith
	//		SQL ) WHERE field LIKE ?1 ESCAPE '\'
	public abstract
	Slice<Board> findByWriterEndingWith(String endingWith);
	
	// 21. 'Not' Operator :
	//		Rule) findBy + <field> + Not
	//		SQL ) WHERE field <> ?1
	public abstract
	List<Board> findBySeqNot(long criteriaSeq);
	
	// 22. 'In' Operator :
	// 	  *Note) Paging query(= return type: Page<T>) needs to have a Pageable parameter
	//		Rule) findBy + <field> + In(Collection<T>)
	//		SQL ) WHERE field IN ( ?1 ) OFFSET ?2 ROWS FETCH FIRST ?3 ROWS ONLY
	public abstract
	Page<Board> findBySeqIn(Collection<Long> seqs, Pageable paging);
	
	// 23. Application of Operators
	//	  *Note) 'OrderBy' operator must Not be used more than *Once in a method name.
	//				  But multiple fields could be ordered by multiple { 'Desc' | 'Asc' } keyword of 'OrderBy' Operator.
	//		SQL)  WHERE title LIKE ?1 ESCAPE '\' OR content LIKE ? ESCAPE '\'
	//				  ORDER BY create_date DESC, board_id
	public abstract
	List<Board> findByTitleLikeOrContentLikeOrderByCreateDateDescSeqAsc(String titlePattern, String contentPattern);
	
} // end interface
