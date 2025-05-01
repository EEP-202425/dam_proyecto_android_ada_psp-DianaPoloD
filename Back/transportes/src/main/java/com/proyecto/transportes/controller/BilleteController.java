package com.proyecto.transportes.controller;

import com.proyecto.transportes.entidades.Billete;
import com.proyecto.transportes.service.BilleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billetes")
@CrossOrigin(origins = "*")
public class BilleteController {

    @Autowired
    private BilleteService service;

    @GetMapping
    public List<Billete> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Billete create(@RequestBody Billete billete) {
        return service.save(billete);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Billete> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Billete> update(@PathVariable Long id, @RequestBody Billete nuevo) {
        return service.update(id, nuevo).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}
