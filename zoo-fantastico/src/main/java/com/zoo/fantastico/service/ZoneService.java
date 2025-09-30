package com.zoo.fantastico.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.zoo.fantastico.repository.ZoneRepository;
import com.zoo.fantastico.model.Zone;
import com.zoo.fantastico.exception.ResourceNotFoundException;

@Service
@Transactional
public class ZoneService {

    private final ZoneRepository repo;

    @Autowired
    public ZoneService(ZoneRepository repo) { this.repo = repo; }

    public Zone createZone(Zone z) {
        return repo.save(z);
    }

    public List<Zone> getAll() { return repo.findAll(); }

    public Zone getById(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Zone not found: " + id)); }

    public Zone updateZone(Long id, Zone data) {
        Zone z = getById(id);
        z.setName(data.getName());
        z.setDescription(data.getDescription());
        z.setCapacity(data.getCapacity());
        return repo.save(z);
    }

    public void deleteZone(Long id) {
        Zone z = getById(id);
        if (z.getCreatures() != null && !z.getCreatures().isEmpty()) {
            throw new IllegalStateException("Cannot delete zone that still has creatures");
        }
        repo.delete(z);
    }
}
