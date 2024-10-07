package org.zerock.myapp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.myapp.domain.Posts;


public interface PostsRepository extends JpaRepository<Posts, Long> {

} // end class
