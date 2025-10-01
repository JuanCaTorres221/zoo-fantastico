package com.zoo.fantastico.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import java.util.List;
import com.zoo.fantastico.model.Zone;
import com.zoo.fantastico.service.ZoneService;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService service;

    @Autowired
    public ZoneController(ZoneService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Zone> create(@Valid @RequestBody Zone z) {
        Zone created = service.createZone(z);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<Zone> all() { return service.getAll(); }

    @GetMapping("/{id}")
    public Zone get(@PathVariable Long id) { return service.getById(id); }

    @PutMapping("/{id}")
    public Zone update(@PathVariable Long id, @Valid @RequestBody Zone z) {
        return service.updateZone(id, z);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteZone(id);
        return ResponseEntity.noContent().build();
    }
}
