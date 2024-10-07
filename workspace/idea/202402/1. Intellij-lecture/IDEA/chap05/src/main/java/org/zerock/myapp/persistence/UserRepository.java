package org.zerock.myapp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.myapp.entity.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    public abstract Optional<User> findByEmail(String email);
} // end interface
