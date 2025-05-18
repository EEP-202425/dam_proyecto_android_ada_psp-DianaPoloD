package com.proyecto.transportes.controller;

import com.proyecto.transportes.entidades.Ruta;
import com.proyecto.transportes.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutas")
@CrossOrigin(origins = "*")
public class RutaController {

    @Autowired
    private RutaService service;

    @GetMapping
    public List<Ruta> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Ruta create(@RequestBody Ruta ruta) {
        return service.save(ruta);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ruta> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/buscar")
    public List<Ruta> buscar(@RequestParam String origen, @RequestParam String destino) {
        return service.buscarPorOrigenYDestino(origen, destino);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Ruta> update(@PathVariable Long id, @RequestBody Ruta nueva) {
        return service.update(id, nueva).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}
