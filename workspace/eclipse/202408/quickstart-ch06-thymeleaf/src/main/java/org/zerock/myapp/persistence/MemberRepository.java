package org.zerock.myapp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.myapp.seq_4.domain.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {
	// 1. Query Methods With Various Operators.
	
	public abstract
	Member findByName(String name);
	
} // end interface
