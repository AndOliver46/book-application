package com.andoliver46.ms.cambioservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andoliver46.ms.cambioservice.models.CambioModel;

public interface CambioRepository extends JpaRepository<CambioModel, Long> {
	
	CambioModel findByFromAndTo(String from, String to);
}
