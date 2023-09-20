package com.hotel.allException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AllExceptionHandler {
	@ExceptionHandler(value = NotFondException.class)
	public ResponseEntity<String>NotFondExceptionHandler(NotFondException notFondException){
		return new ResponseEntity<String>(notFondException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(FormatNotMatchException.class)
	public ResponseEntity<String>FormatNotMatchExceptionHandler(FormatNotMatchException formatNotMatchException) {
		
		return new ResponseEntity<String>(formatNotMatchException.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
	}

}
