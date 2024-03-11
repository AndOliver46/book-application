package com.andoliver46.ms.cambioservice.controller;

import java.math.BigDecimal;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andoliver46.ms.cambioservice.models.CambioModel;
import com.andoliver46.ms.cambioservice.repositories.CambioRepository;

@RestController
@RequestMapping("/cambio-service")
public class CambioController {
	
	private final Environment env;
	private final CambioRepository cambioRepository;

	public CambioController(Environment env, CambioRepository cambioRepository) {
		this.env = env;
		this.cambioRepository = cambioRepository;
	}
	
	@GetMapping("/{amount}/{from}/{to}")
	public CambioModel getCambio(
			@PathVariable("amount") BigDecimal amount,
			@PathVariable("from") String from,
			@PathVariable("to") String to) {
		
		var cambio = cambioRepository.findByFromAndTo(from, to);
		if(cambio == null) throw new RuntimeException("Currency Unsupported");
		
		cambio.setEnvironment("CambioPort: " + env.getProperty("server.port"));
		cambio.calculate(amount);
		
		return cambio;
	}
	
	
}
