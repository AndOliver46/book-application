package com.andoliver46.ms.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "FooBar Endpoint")
@RestController
@RequestMapping("book-service")
public class FooBarController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Operation(summary = "Test resilience4j annotations")
	@GetMapping("/foo-bar")
	//@Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
	//@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
	//@RateLimiter(name = "default", fallbackMethod = "fallbackMethod")
	@Bulkhead(name = "default")
	public String fooBar() {
		LOGGER.info("Request to foo-bar is received!");
		//ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
		//return response.getBody();
		return "foo-bar";
	}
	
	public String fallbackMethod(Exception ex) {
		return "fallbackMethod foo-bar";
	}
	
}
