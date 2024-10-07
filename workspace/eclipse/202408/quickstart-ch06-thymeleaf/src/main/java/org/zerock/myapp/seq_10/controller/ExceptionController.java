package org.zerock.myapp.seq_10.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.myapp.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_9.exception.BoardException;
import org.zerock.myapp.seq_9.exception.BoardNotFoundException;
import org.zerock.myapp.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Controller("exceptionController")
public class ExceptionController extends CommonBeanCallbacks {
	
	
	@GetMapping("/exception/BoardException")
	void raiseBoardException() throws BoardException {
		log.trace("raiseBoardException() invoked.");
		throw new BoardNotFoundException("No Board Found.");
	} // raiseBoardException
	
	@GetMapping("/exception/IllegalArgumentException")
	void raiseIllegalArgumentException() throws  IllegalArgumentException {
		log.trace("raiseIllegalArgumentException() invoked.");
		throw new IllegalArgumentException("Illegal Arguments");
	} // raiseIllegalArgumentException
	
	@GetMapping("/exception/SQLException")
	void raiseSQLException() throws SQLException {
		log.trace("raiseSQLException() invoked.");
		throw new SQLException("SQL syntax error");
	} // raiseSQLException
	
	
	// Local Controller Exception Handler
	@ExceptionHandler(SQLException.class)
	String handleLocalException(Exception e, Model model) {
		log.trace("handleLocalException({}, model) invoked.", e);
		model.addAttribute(SharedAttributes.EXCEPTION, e);
		model.addAttribute(SharedAttributes.STACKTRACE, e.getStackTrace());
		return "/errors/localError.html";
	} // handleLocalException

} // end class


