package com.neosoft.app.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.neosoft.app.controller.UserController;
import com.neosoft.app.response.ErrorResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private final Logger logger = LogManager.getLogger(UserController.class);
	
	@ExceptionHandler(value = UserAlreadyExistsException.class)
	public ResponseEntity<?> handle(UserAlreadyExistsException exception) {
		logger.error(exception.getMessage(), exception);
		ErrorResponse msg = new ErrorResponse(exception.getMessage(), LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(value = FileAlreadyExistException.class)
	public ResponseEntity<?> handle(FileAlreadyExistException exception) {
		logger.error(exception.getMessage(), exception);
		ErrorResponse msg = new ErrorResponse(exception.getMessage(), LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(value = VideoNotFoundException.class)
	public ResponseEntity<?> handle(VideoNotFoundException exception) {
		logger.error(exception.getMessage(), exception);
		ErrorResponse msg = new ErrorResponse(exception.getMessage(), LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(value = SubscriptionPlanAlreadyExistException.class)
	public ResponseEntity<?> handle(SubscriptionPlanAlreadyExistException exception) {
		logger.error(exception.getMessage(), exception);
		ErrorResponse msg = new ErrorResponse(exception.getMessage(), LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(value = SubscriptionCategoryAlreadyExistException.class)
	public ResponseEntity<?> handle(SubscriptionCategoryAlreadyExistException exception) {
		logger.error(exception.getMessage(), exception);
		ErrorResponse msg = new ErrorResponse(exception.getMessage(), LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(value = TopupAlreadyExistException.class)
	public ResponseEntity<?> handle(TopupAlreadyExistException exception) {
		logger.error(exception.getMessage(), exception);
		ErrorResponse msg = new ErrorResponse(exception.getMessage(), LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
