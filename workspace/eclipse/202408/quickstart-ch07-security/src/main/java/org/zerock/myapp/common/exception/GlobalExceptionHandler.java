package org.zerock.myapp.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.zerock.myapp.common.CommonBeanCallbacks;

import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

/**
 * *Important :
 * 		The global exception handler with @ControllerAdvice (or @RestControllerAdvice) + @ExceptionHandler precedes
 * 		the exception handler with mapped to the"/error" with @Controller + `ErrorController` Interface).
 */

@ControllerAdvice				// OK, (1) method, @ControllerAdvice + ResponseEntity<E>
//@RestControllerAdvice		// OK, (2) method, @RestControllerAdvice + @ResponseBody
public class GlobalExceptionHandler extends CommonBeanCallbacks {
	
	/**
	 *  (1) method - @ControllerAdvice + ResponseEntity<E>
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception e, WebRequest req) {
		log.trace("handleGlobalException({}, {}) invoked.", e.toString(), req);
		e.printStackTrace();
		
		ErrorDetails errorDetails = 
				new ErrorDetails(
						HttpStatus.INTERNAL_SERVER_ERROR.value(), 
						e.getMessage(), 
						req.getDescription(true),
						e.getStackTrace()
				);
		
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	} // handleGlobalException
	
	/**
	 * (2) method - @RestControllerAdvice = @ControllerAdvice + @ResponseBody
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Throwable.class)
	public @ResponseBody ErrorDetails handleGlobalThrowable(Throwable t, WebRequest req) {
		log.trace("handleGlobalThrowable({}, {}) invoked.", t.toString(), req);
		e.printStackTrace();
		
		ErrorDetails errorDetails = 
				new ErrorDetails(
						HttpStatus.INTERNAL_SERVER_ERROR.value(), 
						t.getMessage(), 
						req.getDescription(true), 
						t.getStackTrace());
		
		return errorDetails;
	} // handleGlobalThrowable
	 */

} // end class


@Value
final class ErrorDetails {
	private Integer statusCode;
	private String message;
	private String description;
	private StackTraceElement[] stackTrace;
		
} // end class

