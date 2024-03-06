package com.andoliver46.ms.controllers.proxy;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.andoliver46.ms.dtos.CambioDTO;

@FeignClient(name = "cambio-service", url = "http://localhost:8000")
public interface CambioProxy {
	
	@GetMapping("/cambio-service/{amount}/{from}/{to}")
	public CambioDTO getCambio(
			@PathVariable("amount") BigDecimal amount,
			@PathVariable("from") String from,
			@PathVariable("to") String to);
}
