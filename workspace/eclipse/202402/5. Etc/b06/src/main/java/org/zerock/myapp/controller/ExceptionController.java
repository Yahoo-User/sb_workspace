package org.zerock.myapp.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.myapp.exception.BoardException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Controller
public class ExceptionController {
	
	
	@GetMapping("/boardError")
	void boardError() {
		log.debug("boardError() invoked.");
		
		throw new BoardException("boardError");
	} // boardError
	
	
	@GetMapping("/illegalError")
	void illegalError() {
		log.debug("illegalError() invoked.");
		
		throw new IllegalArgumentException("illegalError");
	} // illegalError
	
	
	@GetMapping("/sqlError")
	void sqlError() throws SQLException {
		log.debug("sqlError() invoked.");
		
		throw new SQLException("sqlError");
	} // sqlError
	
	
	@GetMapping("/numberFormatError")
	void numberFormatError() {
		log.debug("numberFormatError() invoked.");
		
		throw new NumberFormatException("numberFormatError");
	} // numberFormatError
	
	
	@ExceptionHandler(NumberFormatException.class)
	String handleNumberFormatError(NumberFormatException e, Model model) {
		log.debug("handleNumberFormatError({}, {}) invoked.", e, model);
		
		model.addAttribute("exception", e);
		
		return "/error2";
	} // handleNumberFormatError

} // end class
