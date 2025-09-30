package com.zoo.fantastico.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;
import com.zoo.fantastico.model.Creature;
import com.zoo.fantastico.service.CreatureService;

@RestController
@RequestMapping("/api/creatures")
public class CreatureController {

    private final CreatureService service;

    @Autowired
    public CreatureController(CreatureService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Creature> create(@Valid @RequestBody Creature c) {
        Creature created = service.createCreature(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<Creature> all() { return service.getAll(); }

    @GetMapping("/{id}")
    public Creature get(@PathVariable Long id) { return service.getById(id); }

    @PutMapping("/{id}")
    public Creature update(@PathVariable Long id, @Valid @RequestBody Creature c) {
        return service.updateCreature(id, c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteCreature(id);
        return ResponseEntity.noContent().build();
    }
}
