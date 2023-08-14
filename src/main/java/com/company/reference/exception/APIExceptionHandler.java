package com.company.reference.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(APIExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
		Map<String, Object> message = new LinkedHashMap<>();
		Map<String, Object> errorResponse = new LinkedHashMap<>();

		exception.getBindingResult().getFieldErrors().forEach(error -> {
			message.put(error.getField(), error.getDefaultMessage());
		});

		errorResponse.put("timestamp", LocalDateTime.now());
		errorResponse.put("status", 400);
		errorResponse.put("error", HttpStatus.BAD_REQUEST);
		errorResponse.put("message", message);

		LOGGER.error("Requested payload is not valid: {}", errorResponse);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
		Map<String, Object> errorResponse = new LinkedHashMap<>();

		errorResponse.put("timestamp", LocalDateTime.now());
		errorResponse.put("status", 400);
		errorResponse.put("error", HttpStatus.BAD_REQUEST);
		errorResponse.put("message", "It seems that a same email or phone number is already exist");

		LOGGER.error("Requested email or phone number is already registered: {}", errorResponse);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Map<String, Object>> handleMissingServletRequestParameter(
			MissingServletRequestParameterException exception) {
		Map<String, Object> errorResponse = new LinkedHashMap<>();

		errorResponse.put("timestamp", LocalDateTime.now());
		errorResponse.put("status", 400);
		errorResponse.put("error", HttpStatus.BAD_REQUEST);
		errorResponse.put("message", exception.getMessage());

		LOGGER.error("Request parameter is missing in url: {}", errorResponse);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleEmployeeNotFound(EmployeeNotFoundException exception) {
		Map<String, Object> errorResponse = new LinkedHashMap<>();

		errorResponse.put("timestamp", LocalDateTime.now());
		errorResponse.put("status", 404);
		errorResponse.put("error", HttpStatus.NOT_FOUND);
		errorResponse.put("message", exception.getMessage());

		LOGGER.error("Requested employee(s) not found: {}", errorResponse);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

}
