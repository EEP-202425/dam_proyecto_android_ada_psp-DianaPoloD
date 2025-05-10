package com.proyecto.transportes.service;

import com.proyecto.transportes.entidades.Billete;
import com.proyecto.transportes.repository.BilleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BilleteService {

    @Autowired
    private BilleteRepository repo;

    public List<Billete> getAll() {
        return repo.findAll();
    }

    public Billete save(Billete billete) {
        return repo.save(billete);
    }
    
    public Optional<Billete> getById(Long id) {
        return repo.findById(id);
    }

    public Optional<Billete> update(Long id, Billete nuevo) {
        return repo.findById(id).map(existing -> {
            existing.setPasajero(nuevo.getPasajero());
            existing.setAutobus(nuevo.getAutobus());
            existing.setFechaCompra(nuevo.getFechaCompra());
            existing.setPrecio(nuevo.getPrecio());
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
