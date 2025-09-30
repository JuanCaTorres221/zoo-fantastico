package com.zoo.fantastico.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.zoo.fantastico.repository.CreatureRepository;
import com.zoo.fantastico.model.Creature;
import com.zoo.fantastico.exception.ResourceNotFoundException;

@Service
@Transactional
public class CreatureService {

    private final CreatureRepository repo;

    @Autowired
    public CreatureService(CreatureRepository repo) {
        this.repo = repo;
    }

    public Creature createCreature(Creature c) {
        if (c.getSize() < 0) throw new IllegalArgumentException("size must be >= 0");
        return repo.save(c);
    }

    public List<Creature> getAll() {
        return repo.findAll();
    }

    public Creature getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Creature not found: " + id));
    }

    public Creature updateCreature(Long id, Creature data) {
        Creature c = getById(id);
        c.setName(data.getName());
        c.setSpecies(data.getSpecies());
        c.setSize(data.getSize());
        c.setDangerLevel(data.getDangerLevel());
        c.setHealthStatus(data.getHealthStatus());
        c.setZone(data.getZone());
        return repo.save(c);
    }

    public void deleteCreature(Long id) {
        Creature c = getById(id);
        if ("critical".equalsIgnoreCase(c.getHealthStatus())) {
            throw new IllegalStateException("Cannot delete creature in critical health");
        }
        repo.delete(c);
    }
}
