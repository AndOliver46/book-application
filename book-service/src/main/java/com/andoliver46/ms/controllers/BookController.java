package com.andoliver46.ms.controllers;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andoliver46.ms.controllers.proxy.CambioProxy;
import com.andoliver46.ms.models.BookModel;
import com.andoliver46.ms.repositories.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	private final BookRepository bookRepository;
	private final CambioProxy cambioProxy;
	private final Environment environment;
	
	public BookController(BookRepository bookRepository, CambioProxy cambioProxy, Environment environment) {
		this.bookRepository = bookRepository;
		this.cambioProxy = cambioProxy;
		this.environment = environment;
	}
	
	@GetMapping("/{id}/{currency}")
	public BookModel findBook(@PathVariable Long id, @PathVariable String currency) {
		
		var book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found."));
		
//		var params = new HashMap<String, String>();
//		params.put("amount", book.getPrice().toPlainString());
//		params.put("from", "USD");
//		params.put("to", currency);
//		
//		var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", CambioDTO.class, params);
//		CambioDTO cambio = response.getBody();
		
		var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);
		
		book.setPrice(cambio.getConvertedValue());
		book.setCurrency(cambio.getCurrencyTo());
		book.setEnvironment(cambio.getEnvironment() + ", BookPort: " + environment.getProperty("server.port"));
		
		return book;
	}

}
