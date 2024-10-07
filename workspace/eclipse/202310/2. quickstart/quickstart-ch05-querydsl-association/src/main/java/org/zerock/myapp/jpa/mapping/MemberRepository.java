package org.zerock.myapp.jpa.mapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository
        //extends JpaRepository<Member, String> {   // Supported by Spring Data JPA
        extends CrudRepository<Member, String> {    // Supported by Spring Data JDBC
    ;;
} // end interface
