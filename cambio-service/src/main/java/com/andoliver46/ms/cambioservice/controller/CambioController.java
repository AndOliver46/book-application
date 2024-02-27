package com.andoliver46.ms.cambioservice.controller;

import java.math.BigDecimal;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andoliver46.ms.cambioservice.models.CambioModel;

@RestController
@RequestMapping("/cambio-service")
public class CambioController {
	
	private final Environment env;

	public CambioController(Environment env) {
		this.env = env;
	}
	
	@GetMapping("/{amount}/{from}/{to}")
	public CambioModel getCambio(
			@PathVariable("amount") BigDecimal amount,
			@PathVariable("from") String from,
			@PathVariable("to") String to) {
		
		return new CambioModel(1L, from, to, BigDecimal.ONE, BigDecimal.ONE, "Port: " + env.getProperty("server.port"));
	}
	
	
}
