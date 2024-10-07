package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.persistence.MemberRepository;

import java.util.Optional;


@Log4j2
@NoArgsConstructor

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired private MemberRepository memberRepository;


    @Override
    public Member getMember(Member member) {
        log.trace("getMember({}) invoked.", member);

        Optional<Member> optionalMember = this.memberRepository.findById(member.getId());
        return optionalMember.orElse(null);
    } // getMember

} // end class
