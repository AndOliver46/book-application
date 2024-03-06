package com.andoliver46.ms.controllers;

import java.util.Date;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andoliver46.ms.models.BookModel;
import com.andoliver46.ms.repositories.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	private final Environment env;
	private final BookRepository bookRepository;
	
	public BookController(Environment env, BookRepository bookRepository) {
		this.env = env;
		this.bookRepository = bookRepository;
	}
	
	@GetMapping("/{id}/{currency}")
	public BookModel findBook(@PathVariable Long id, @PathVariable String currency) {
		
		var book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found."));
		
		String port = env.getProperty("server.port");
		book.setEnvironment(port);
		
		return book;
	}

}
