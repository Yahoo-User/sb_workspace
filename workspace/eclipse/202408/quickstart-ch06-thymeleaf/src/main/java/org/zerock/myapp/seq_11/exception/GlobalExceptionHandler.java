package org.zerock.myapp.seq_11.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zerock.myapp.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_9.exception.BoardException;
import org.zerock.myapp.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@ControllerAdvice
public class GlobalExceptionHandler extends CommonBeanCallbacks {
	
	
	@ExceptionHandler( { BoardException.class } )
	String handleCustomException(BoardException e, Model model) {
		log.trace("handleCustomException({}, {}) invoked.", e, model);
		
		model.addAttribute(SharedAttributes.EXCEPTION, e);
		
		return "/errors/customErrror";
	} // handleCustomException
	
	@ExceptionHandler(Exception.class)
	String handleException(Exception e, Model model) {
		log.trace("handleException({}, {}) invoked.", e, model);
		
		model.addAttribute(SharedAttributes.EXCEPTION, e);
		model.addAttribute(SharedAttributes.STACKTRACE, e.getStackTrace());
		
		return "/errors/globalError";
	} // handleException

} // end class

