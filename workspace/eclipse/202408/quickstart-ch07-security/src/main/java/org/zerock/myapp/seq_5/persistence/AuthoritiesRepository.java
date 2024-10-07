package org.zerock.myapp.seq_5.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.myapp.seq_4.domain.Authorities;
import org.zerock.myapp.seq_4.domain.AuthoritiesKey;


public interface AuthoritiesRepository extends JpaRepository<Authorities, AuthoritiesKey> {
	
	// 1. Query Methods With Various Operators
	public abstract List<Authorities> findByUsername(String username);

} // end interface

