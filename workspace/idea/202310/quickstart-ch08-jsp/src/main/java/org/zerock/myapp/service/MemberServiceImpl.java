package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.persistence.MemberRepository;

import java.util.Objects;


@Log4j2
@NoArgsConstructor

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired private MemberRepository memberRepository;


    @Override
    public Member select(Member member) throws ServiceException {
        log.trace("select({}) invoked.", member);

        try {
            Objects.requireNonNull(member.getId());
            return this.memberRepository.findById(member.getId()).orElseThrow();
        } catch(Exception e) {
            throw new ServiceException(e);
        } // try-catch
    } // select

} // end class
