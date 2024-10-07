package org.zerock.myapp.persistence;

import org.springframework.data.repository.CrudRepository;
import org.zerock.myapp.domain.Member;


public interface MemberDao extends CrudRepository<Member, String> {} // end interface
