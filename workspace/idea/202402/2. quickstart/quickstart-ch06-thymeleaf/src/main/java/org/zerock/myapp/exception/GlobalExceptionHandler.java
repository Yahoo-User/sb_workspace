package org.zerock.myapp.exception;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Log4j2

// Class contains `required fields`, you have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BoardException.class)
    String handleBoardException(BoardException e, Model model) {
        log.trace("handleBoardException({}, {}) invoked.", e, model);

        model.addAttribute("exception", e);
        return "errors/boardError";
    } // handleBoardException


    @ExceptionHandler(Exception.class)
    String handleException(Exception e, Model model) {
        log.trace("handleException({}, {}) invoked.", e, model);

        model.addAttribute("exception", e);
        return "errors/globalError";
    } // handleException


} // end class
