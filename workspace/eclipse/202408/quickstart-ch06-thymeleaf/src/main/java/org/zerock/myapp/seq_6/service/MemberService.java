package org.zerock.myapp.seq_6.service;

import org.zerock.myapp.seq_4.domain.Member;


public interface MemberService {

	public abstract
	Member findById(Long id);
	
	public abstract
	Member findByName(String name);

} // end interface

