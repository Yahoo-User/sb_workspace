package org.zerock.myapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.zerock.myapp.domain.Member;



public interface MemberRepository
	extends CrudRepository<Member, String> {

} // end interface
