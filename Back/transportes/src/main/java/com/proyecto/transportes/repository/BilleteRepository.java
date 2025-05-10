package com.proyecto.transportes.repository;

import com.proyecto.transportes.entidades.Billete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BilleteRepository extends JpaRepository<Billete, Long> {
}
