package com.proyecto.transportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.transportes.entidades.Autobus;

public interface AutobusRepository extends JpaRepository<Autobus, Long> {
}
