package com.proyecto.transportes.controller;

import com.proyecto.transportes.entidades.Autobus;
import com.proyecto.transportes.service.AutobusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autobuses")
@CrossOrigin(origins = "*")
public class AutobusController {

    @Autowired
    private AutobusService service;

    @GetMapping
    public List<Autobus> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Autobus create(@RequestBody Autobus autobus) {
        return service.save(autobus);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Autobus> getById(@PathVariable Long id) {
        return service.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autobus> update(@PathVariable Long id, @RequestBody Autobus nuevo) {
        return service.update(id, nuevo)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}
