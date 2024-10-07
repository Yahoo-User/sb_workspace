package org.zerock.myapp.persistence;

import org.springframework.data.repository.CrudRepository;
import org.zerock.myapp.domain.User;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
} // end interface
