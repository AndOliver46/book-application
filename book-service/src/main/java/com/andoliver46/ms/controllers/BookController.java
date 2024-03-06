package com.andoliver46.ms.controllers;

import java.util.Date;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andoliver46.ms.models.Book;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	private final Environment env;
	
	public BookController(Environment env) {
		this.env = env;
	}
	
	@GetMapping("/{id}/{currency}")
	public Book findBook(@PathVariable Long id, @PathVariable String currency) {
		
		String port = env.getProperty("server.port");
		
		return new Book(id, "Nigel Poulton", "Docker Deep Dive", new Date(), Double.valueOf(13.07), currency, port);
	}

}
