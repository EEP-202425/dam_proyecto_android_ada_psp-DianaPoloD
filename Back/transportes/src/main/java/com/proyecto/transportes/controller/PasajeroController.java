package com.proyecto.transportes.controller;

import com.proyecto.transportes.entidades.Pasajero;
import com.proyecto.transportes.service.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pasajeros")
@CrossOrigin(origins = "*")
public class PasajeroController {

    @Autowired
    private PasajeroService service;

    @GetMapping
    public List<Pasajero> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Pasajero create(@RequestBody Pasajero pasajero) {
        return service.save(pasajero);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pasajero> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pasajero> update(@PathVariable Long id, @RequestBody Pasajero nuevo) {
        return service.update(id, nuevo).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}
