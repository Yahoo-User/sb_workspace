package org.zerock.myapp.seq_10.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.zerock.myapp.seq_0.common.CommonBeanCallbacks;

import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

/**
 * *Important :
 * 		The global exception handler with @ControllerAdvice (or @RestControllerAdvice) + @ExceptionHandler precedes
 * 		the exception handler with mapped to the"/error" (@Controller + `ErrorController` Interface).
 */

//@ControllerAdvice				// OK, (1) method
//@RestControllerAdvice		// OK, (2) method, @ControllerAdvice + @ResponseBody
public final class CommonGlobalExceptionHandler extends CommonBeanCallbacks {
	
	/**
	 *  (1) method
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllExceptions(Exception e, WebRequest req) {
		log.trace("handleThrowable({}, {}) invoked.", e, req);
		
		ErrorDetails errorDetails = 
				new ErrorDetails(
						HttpStatus.INTERNAL_SERVER_ERROR.value(), 
						e.getMessage(), 
						req.getDescription(true)
				);
		
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	} // handleThrowable
	
	/**
	 * (2) method, @RestControllerAdvice = @ControllerAdvice + @ResponseBody
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Throwable.class)
	public @ResponseBody ErrorDetails handleThrowable(Throwable t, WebRequest req) {
		log.trace("handleThrowable({}, {}) invoked.", t, req);
		
		ErrorDetails errorDetails = 
			new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), t.getMessage(), req.getDescription(true));
		
		return errorDetails;
	} // handleThrowable
	 */

} // end class


@Value
final class ErrorDetails {
	private Integer statusCode;
	private String message;
	private String description;
		
} // end class

