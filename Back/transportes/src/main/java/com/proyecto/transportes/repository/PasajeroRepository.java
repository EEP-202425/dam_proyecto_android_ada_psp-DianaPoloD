package com.proyecto.transportes.repository;

import com.proyecto.transportes.entidades.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
}
