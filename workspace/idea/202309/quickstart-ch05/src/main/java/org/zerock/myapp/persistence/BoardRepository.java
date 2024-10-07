package org.zerock.myapp.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.zerock.myapp.domain.Board;

import java.util.List;


public interface BoardRepository extends CrudRepository<Board, Long> {

    // 1. Find all entities with title equals to the keyword.
    public abstract List<Board> findByTitle(String title);

    // 2. Find all entities with content containing keyword.
    public abstract List<Board> findByContentContaining(String content);

    // 3. Find all entities with containing title and content with matching the keyword.
    public abstract List<Board> findByTitleContainingOrContentContaining(String title, String content);

    // 4. Find all entities with containing title and descend ordered by seq.
    public abstract List<Board> findByTitleContainingOrderBySeqDesc(String title);

    // 5. Find all entities containing title with paging.
    public abstract List<Board> findByTitleContaining(String title, Pageable paging);

    // 6. Find all entities containing title and descending ordered by seq with paging.
    public abstract List<Board> findByTitleContainingOrderBySeqDesc(String title, Pageable paging);

    // 7. Find all entities containing content with paging.
    public abstract List<Board> findByWriter(String writer, Pageable paging);

    // 8. Find all entities by containing writer with Paging and returns Page<T>.
    public abstract Page<Board> findByWriterContaining(String writer, Pageable paging);

} // end interface



