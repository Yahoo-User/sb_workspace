package org.zerock.myapp.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.myapp.domain.Board;

import java.util.List;


public interface QueryAnnotationRepository extends CrudRepository<Board, Long> {


    // -----------------------------------------------------------------------
    // 1. `@Query` Annotation Including `JPQL` With `Location-based` Parameter #1
    // -----------------------------------------------------------------------
    // Note1 - JPQL uses `entity & fields`, *Not* `table & columns`.
    // Note2 - `?1` is a `location-based` Parameter (Started With 1)

    @Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")
    List<Board> queryAnnotation1(String keyword);


    // -----------------------------------------------------------------------
    // 2. `@Query` Annotation Including `JPQL` With `Name-based` Parameter #2
    // -----------------------------------------------------------------------
    // Note1 - `JPQL` uses `entity & fields`, *Not* `table & columns`.
    // Note2 - `:name` is a `name-based` Parameter

    @Query("SELECT b FROM Board b WHERE b.title LIKE %:keyword% ORDER BY b.seq DESC")
    List<Board> queryAnnotation2(String keyword);


    // -----------------------------------------------------------------------
    // 3. `@Query` Annotation Including `JPQL` With the `Specified` Fields #3
    //      and `Location-based` Parameter.
    // -----------------------------------------------------------------------
    // Note1 - `JPQL` uses `entity & fields`, *Not* `table & columns`.

    @Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")
    List<Object[]> queryAnnotation3(String keyword);


    // -----------------------------------------------------------------------
    // 4. `@Query` Annotation With `Native` Query #4
    // -----------------------------------------------------------------------
    // Note1 - `Native Query` uses `table & columns`, *Not* `entity & fields`.

    @Query(
        value = "SELECT seq, title, writer, create_date FROM board WHERE title LIKE '%'||?1||'%' ORDER BY seq DESC",
        nativeQuery = true
    )
    List<Object[]> queryAnnotation4(String keyword);


    // -----------------------------------------------------------------------
    // 5. `@Query` Annotation With `Paging` & `Location-based` Parameter #5
    // -----------------------------------------------------------------------
    // Note1 - `Native Query` uses `table & columns`, *Not* `entity & fields`.

//    @Query(
//        value = "SELECT seq, title, writer, create_date FROM board WHERE writer LIKE '%'||?1||'%'",
//        nativeQuery = true
//    )
//    @Query(
//        value = "SELECT seq, title, writer, create_date FROM board WHERE writer LIKE '%'||?1||'%' ORDER BY seq DESC",
//        nativeQuery = true
//    )
    @Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b WHERE b.writer LIKE '%'||?1||'%'")
    List<Object[]> queryAnnotation5(String writer, Pageable paging);

} // end interface
