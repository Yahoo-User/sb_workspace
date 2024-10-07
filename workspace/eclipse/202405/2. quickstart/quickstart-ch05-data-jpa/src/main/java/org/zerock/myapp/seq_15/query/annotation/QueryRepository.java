package org.zerock.myapp.seq_15.query.annotation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.seq_6.domain.Board;


/**
 * ---------------------------------------
 * 1. @Query Annotation Of Spring Data JPA
 * ---------------------------------------
 * 	(1) For JPQL 			(Java Persistence Query Language)
 * 	(2) For Native SQL 	(Database-specific SQL)
 * ---------------------------------------
 * Annotation to declare finder queries directly on repository methods :
 * 	(1) `value` attribute - defines the JPA query to be executed when the annotated method is called.
 * 	(2) `name` attribute - defines the named query to be used.
 * 		  If Not defined, a `jakarta.persistence.NamedQuery` with name of $ domainClass.${queryMethodName}} will be used.
 * (3) `countQuery` attribute - defines a special count query that shall be used for pagination queries to lookup the total number of elements fora page. 
 * 		  If none is configured we will derive the count query from the `original` query or `countProjection()` query if any.
 * (4) `nativeQuery` attribute - configures whether the given query is a native one. ( Defaults to false ).
 * (5) `countName` attribute - returns the name of the `jakarta.persistence.NamedQuery` to be used to execute count queries when pagination is used.
 * 		  Will default to the named query name configured suffixed by .count.
 * (6) `countProjection` attribute - defines the projection part of the count query that is generated for pagination.
 * 		  If neither `countQuery()` nor `countProjection()` is configured, we will derive the count query from the original query.
 * 
 * See Also:
 * 		@Modifying
 * 
 * ---------------------------------------
 * 2. @Modifying Annotation Of Spring Data JPA
 * ---------------------------------------
 * Indicates a query method should be considered as modifying query as that changes the way it needs to be executed.
 * This annotation is only considered if used on query methods defined through a Query annotation.
 * It's not applied on custom implementation methods or queries derived from the method name
 * as they already have control overthe underlying data access APIs or specify if they are modifying by their name. 
 * Queries that require a `@Modifying` annotation include INSERT, UPDATE, DELETE, and DDLstatements.
 * 
 * See Also:
 * 		@Query
 * 
 * ---------------------------------------
 * 3. Mix of Location-based & Name-based Parameters
 * ---------------------------------------
 * It raises exception.	(***)
 * 
 */

public interface QueryRepository extends JpaRepository<Board, Long> {
	
	// (1) Location-based Parameters (ex: ?1, ?2, ?3, ...)
	public abstract
	@Query("SELECT b FROM Board b WHERE b.title LIKE ?1 ESCAPE '/' ORDER BY b.seq DESC")
	List<Board> findByJPQLWithLocationBasedParameters(String titlePattern);

	// (2) Name-based Parameters (ex - :name1, :name2, ...)
	public abstract
	@Query("SELECT b FROM Board b WHERE b.title LIKE :titlePattern ORDER BY b.seq DESC")
	List<Board> findByJPQLWithNameBasedParameters(String titlePattern);
	
	public abstract
	@Query("SELECT b FROM Board b WHERE b.title LIKE :titlePattern ORDER BY b.seq DESC")
	List<Board> findByJPQLWithNameBasedParametersAndParamAnnotation(@Param("titlePattern") String pattern);
	
	// (3) SELECT some fields, NOT all fields in the JPQL with location-based & name-based parameters
	public abstract
	@Query("SELECT b.seq, b.title, b.writer, b.createDate, b.cnt FROM Board b WHERE b.seq BETWEEN ?1 AND ?2 ORDER BY b.seq DESC")
	Slice<Object[]> findByJPQLWithSomeFields(long criteriaStartSeq, long criteriaEndSeq);

	// (4) Modify Entities With @Modifying & @Transactional annotations
	@Transactional
	@Modifying
	public abstract
	@Query("UPDATE Board b SET b.updateDate = current_timestamp, b.cnt = :cnt WHERE b.seq IN (:listOfSeq)")
	int updateDateOfEntities(@Param("cnt") int updateCnt, @Param("listOfSeq") long ... seqs);
	
	// (5) Native Query
	public abstract
	@Query(value="SELECT board_id, title, writer, create_date, cnt FROM board WHERE title LIKE ?1 ESCAPE '/' ORDER BY board_id DESC", nativeQuery=true)
	Slice<Object[]> findByNativeSQLWithSomeFields(String titlePattern);
	
	// (6) Native SQL with Paging & Sorting
	//		*Note) Paging query(= Return type: Page<T>) needs to have a `Pageable` parameter
	//		Usage) PageRequest paging = PageRequest.of(currPage, linesPerPage, Sort.Direction.ASC | DESC, "<field to sort>")
	public abstract
	@Query(value="SELECT * FROM board ORDER BY board_id DESC", nativeQuery=true)
	Page<Board> findAllbyNativeSQLWithPagingAndSorting(Pageable paging);
	
	
	
} // end interface
