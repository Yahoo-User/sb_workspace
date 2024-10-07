package org.zerock.myapp.security.service;

import org.zerock.myapp.security.dao.UserDAO;
import org.zerock.myapp.security.domain.UserDTO;
import org.zerock.myapp.security.domain.UserVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class UserService {

	// Important: 
	//		The `@PostConstruct` & `@PreDestroy` annotations do work *Only in the java *Servlet class.	
	
	public UserVO findByUsername(UserDTO dto) throws Exception {
		log.trace("findByUsername({}) invoked.", dto);
		
		UserDAO dao = new UserDAO();
		
		UserVO vo = dao.selectByUsername(dto);
		log.info("\t+ vo: {}", vo);
		
		return vo;
	} // findByUsername

} // end class
