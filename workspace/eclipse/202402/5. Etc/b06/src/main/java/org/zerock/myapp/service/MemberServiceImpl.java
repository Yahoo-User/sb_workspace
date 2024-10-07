package org.zerock.myapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.persistence.MemberDao;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service
public class MemberServiceImpl
	implements MemberService {

	@Autowired
	private MemberDao dao;
	
	
	@Override
	public Member getMember(Member member) {
		log.debug("getMember({}) invoked.", member);
		
		Optional<Member> optional = this.dao.findById(member.getId());
		
		if(optional.isPresent()) {
			return optional.get();
		} else return null;
	} // getMember

} // end class
