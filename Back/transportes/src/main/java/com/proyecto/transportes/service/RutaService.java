package com.proyecto.transportes.service;

import com.proyecto.transportes.entidades.Ruta;
import com.proyecto.transportes.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RutaService {

	@Autowired
	private RutaRepository repo;

	public List<Ruta> getAll() {
		return repo.findAll();
	}

	public Ruta save(Ruta ruta) {
		return repo.save(ruta);
	}

	public Optional<Ruta> getById(Long id) {
		return repo.findById(id);
	}

	public Optional<Ruta> update(Long id, Ruta nueva) {
		return repo.findById(id).map(existing -> {
			existing.setOrigen(nueva.getOrigen());
			existing.setDestino(nueva.getDestino());
			existing.setHorarioSalida(nueva.getHorarioSalida());
			existing.setHorarioLlegada(nueva.getHorarioLlegada());
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

	public List<Ruta> buscarPorOrigenYDestino(String origen, String destino) {
		return repo.findByOrigenAndDestino(origen, destino);
	}

}
