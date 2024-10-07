package org.zerock.myapp.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.myapp.domain.Board;

import java.util.List;


public interface QueryAnnotationRepository extends CrudRepository<Board, Long> {

    // 1. @Query annotation including JPQL with location-based parameter - 1
    //
    // *NOTE1: JPQL uses entity & fields, *NOT* table & columns.
    // *NOTE2: `?1`     - Location-based Parameter (started with 1)
    @Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")
    List<Board> queryAnnotation1(String keyword);

    // 2. @Query annotation including JPQL with name-based parameter - 2
    //
    // *NOTE1: JPQL uses entity & fields, *NOT* table & columns.
    // *NOTE2: `:name`  - Name-based Parameter
    @Query("SELECT b FROM Board b WHERE b.title LIKE %:keyword% ORDER BY b.seq DESC")
    List<Board> queryAnnotation2(String keyword);

    // 3. @Query annotation including JPQL with the specified fields - 3
    //
    // *NOTE1: JPQL uses entity & fields, *NOT* table & columns.
    @Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")
    List<Object[]> queryAnnotation3(String keyword);

    // 4. @Query annotation with native query - 4
    //
    // *NOTE1: Native query Uses table & columns, *NOT* entity & fields.
    @Query(value = "SELECT seq, title, writer, create_date FROM board WHERE title LIKE '%'||?1||'%' ORDER BY seq DESC", nativeQuery = true)
    List<Object[]> queryAnnotation4(String keyword);

    // 5. @Query annotation with Paging - 5
    //
    // *NOTE1: Native query Uses table & columns, *NOT* entity & fields.
//    @Query(value = "SELECT seq, title, writer, create_date FROM board WHERE writer LIKE '%'||?1||'%'", nativeQuery = true)
//    @Query(value = "SELECT seq, title, writer, create_date FROM board WHERE writer LIKE '%'||?1||'%' ORDER BY seq DESC", nativeQuery = true)
    @Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b WHERE b.writer LIKE '%'||?1||'%'")
    List<Object[]> queryAnnotation5(String writer, Pageable paging);

} // end interface
