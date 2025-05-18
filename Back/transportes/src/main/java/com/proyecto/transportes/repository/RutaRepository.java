package com.proyecto.transportes.repository;

import com.proyecto.transportes.entidades.Ruta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RutaRepository extends JpaRepository<Ruta, Long> {
	List<Ruta> findByOrigenAndDestino(String origen, String destino);

}


