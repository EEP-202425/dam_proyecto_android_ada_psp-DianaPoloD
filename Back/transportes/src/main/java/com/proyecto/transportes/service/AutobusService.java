package com.proyecto.transportes.service;

import com.proyecto.transportes.entidades.Autobus;
import com.proyecto.transportes.repository.AutobusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutobusService {

	@Autowired
	private AutobusRepository repo;

	public List<Autobus> getAll() {
	    return repo.findAll();
	}



	public Autobus save(Autobus autobus) {
		return repo.save(autobus);
	}

	public Optional<Autobus> getById(Long id) {
		return repo.findById(id);
	}

	public Optional<Autobus> update(Long id, Autobus actualizado) {
		return repo.findById(id).map(existing -> {
			existing.setTipo(actualizado.getTipo());
			existing.setCapacidad(actualizado.getCapacidad());
			existing.setRuta(actualizado.getRuta());
			return repo.save(existing);
		});
	}

	public boolean delete(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}

}
