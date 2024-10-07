package org.zerock.myapp.seq_4.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.myapp.seq_3.domain.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {

} // end interface


