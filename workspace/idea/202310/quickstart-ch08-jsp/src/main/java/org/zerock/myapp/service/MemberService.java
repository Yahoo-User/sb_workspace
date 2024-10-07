package org.zerock.myapp.service;

import org.zerock.myapp.domain.Member;
import org.zerock.myapp.exception.ServiceException;


public interface MemberService {
    Member select(Member member) throws ServiceException;
} // end interface
