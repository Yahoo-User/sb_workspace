package org.zerock.myapp.jpa.mapping;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MyBoardRepository
        //extends JpaRepository<MyBoard, Long> {   // Supported by Spring Data JPA
        extends CrudRepository<MyBoard, Long> {    // Supported by Spring Data JDBC
    ;;
} // end interface
