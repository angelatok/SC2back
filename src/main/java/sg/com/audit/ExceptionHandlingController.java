package sg.com.audit;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

	
	/* @ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
	        ExceptionResponse response = new ExceptionResponse();
	        response.setErrorCode("Validation Error");
	        response.setErrorMessage(ex.getMessage());
	        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	 }*/
	
	 @ExceptionHandler(IllegalArgumentException.class)
	 public ResponseEntity<ExceptionResponse> invalidInput(IllegalArgumentException ex) {
	        ExceptionResponse response = new ExceptionResponse();
	        response.setErrorCode("Validation Error");
	        response.setErrorMessage(ex.getMessage());
	        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.IM_USED);
	 }
	 
	 @ExceptionHandler(NoSuchElementException.class)
	 public ResponseEntity<ExceptionResponse> invalidInput(NoSuchElementException ex) {

		 ExceptionResponse response = new ExceptionResponse();
		 response.setErrorCode("Id Not Found");
		 response.setErrorMessage(ex.getLocalizedMessage());

		 return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	 }

}
