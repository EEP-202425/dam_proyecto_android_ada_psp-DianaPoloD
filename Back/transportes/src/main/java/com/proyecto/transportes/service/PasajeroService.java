package com.proyecto.transportes.service;

import com.proyecto.transportes.entidades.Pasajero;
import com.proyecto.transportes.repository.PasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasajeroService {

    @Autowired
    private PasajeroRepository repo;

    public List<Pasajero> getAll() {
        return repo.findAll();
    }

    public Pasajero save(Pasajero pasajero) {
        return repo.save(pasajero);
    }
    
    public Optional<Pasajero> getById(Long id) {
        return repo.findById(id);
    }

    public Optional<Pasajero> update(Long id, Pasajero nuevo) {
        return repo.findById(id).map(existing -> {
            existing.setNombre(nuevo.getNombre());
            existing.setApellido(nuevo.getApellido());
            existing.setEmail(nuevo.getEmail());
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
