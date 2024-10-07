package org.zerock.myapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.common.CommonBeanCallbacks;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.persistence.MemberRepository;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service("memberService")
public class MemberServiceImpl 
	extends CommonBeanCallbacks implements MemberService {
	@Autowired(required = true) private MemberRepository memberRepo;

	
	@Override
	public
	void postConstruct() {
		log.trace("(2) postConstruct( {} ) invoked.", this.beanName);
		log.info("  + this.memberRepo: {}, type: {}", this.memberRepo, this.memberRepo.getClass().getName());
	} // postConstruct
	
	
	@Override
	public
	Member findById(Long id) {
		log.trace("findMemberById({}) invoked.", id);
		
		Optional<Member> member = this.memberRepo.findById(id);
		return member.orElse(null);
	} // findMemberById

	@Override
	public Member findByName(String name) {
		log.trace("findByName({}) invoked.", name);
		return this.memberRepo.findByName(name);
	} // findByName
	
	
} // end class


