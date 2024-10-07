package org.zerock.myapp.seq_5.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.myapp.seq_4.domain.Users;


public interface UsersRepository extends JpaRepository<Users, String> {
	// 1. Query Methods With Various Operators

} // end interface

