package com.andoliver46.ms.controllers;

import java.util.HashMap;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.andoliver46.ms.dtos.CambioDTO;
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
		
		var params = new HashMap<String, String>();
		params.put("amount", book.getPrice().toPlainString());
		params.put("from", "USD");
		params.put("to", currency);
		
		var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", CambioDTO.class, params);
		CambioDTO cambio = response.getBody();
		
		book.setPrice(cambio.getConvertedValue());
		book.setCurrency(cambio.getCurrencyTo());
		book.setEnvironment(cambio.getEnvironment());
		
		return book;
	}

}
