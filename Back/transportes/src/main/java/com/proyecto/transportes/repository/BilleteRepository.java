package com.proyecto.transportes.repository;

import com.proyecto.transportes.entidades.Billete;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BilleteRepository extends JpaRepository<Billete, Long> {
	List<Billete> findByAutobusId(Long id);

}
