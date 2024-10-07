package org.zerock.myapp.service;

import org.zerock.myapp.domain.Member;


public interface MemberService {

	public abstract
	Member findById(Long id);
	
	public abstract
	Member findByName(String name);

} // end interface

