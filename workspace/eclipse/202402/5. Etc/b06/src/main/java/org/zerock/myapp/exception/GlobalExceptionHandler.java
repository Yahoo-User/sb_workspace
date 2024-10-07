package org.zerock.myapp.exception;

import java.sql.SQLException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(BoardException.class)
	String handleCustomException(BoardException e, Model model) {
		log.debug("handleCustomException({}, {}) invoked.", e, model);
		
		model.addAttribute("exception", e);
		
		return "/error";
	} // handleCustomException
	
	
	@ExceptionHandler({IllegalArgumentException.class, SQLException.class})
	String handleException(Exception e, Model model) {
		log.debug("handleException({}, {}) invoked.", e, model);
		
		model.addAttribute("exception", e);
		
		return "/error";
	} // handleException

} // end class
