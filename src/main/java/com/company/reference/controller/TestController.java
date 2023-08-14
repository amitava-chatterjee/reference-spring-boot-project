package com.company.reference.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class TestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@GetMapping("/test1")
	public String test1() {
		LOGGER.info("This is a info log");
		LOGGER.warn("This is a warn log");
		return "This is a message";
	}

	@GetMapping(path = "/test2")
	public ResponseEntity<String> test2() {
		String message = "This is a message";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "This is a custom header supplied by me");
		return new ResponseEntity<String>(message, headers, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/test3")
	public ResponseEntity<String> test3() {
		String message = "This is a message";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "This is a custom header supplied by me");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(message);
	}

	@GetMapping("/test4")
	public ResponseEntity<String> test4() {
		String message = "This is a message";
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}

	@GetMapping("/test5")
	public ResponseEntity<String> test5() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "It seems something is created");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(headers).build();
	}

	@GetMapping("/test6")
	public ResponseEntity<String> test6() {
		String message = "This is a message";
		return ResponseEntity.ok(message);
	}

}
