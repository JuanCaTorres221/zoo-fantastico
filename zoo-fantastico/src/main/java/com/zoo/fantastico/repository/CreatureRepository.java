package com.zoo.fantastico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zoo.fantastico.model.Creature;

public interface CreatureRepository extends JpaRepository<Creature, Long> { }
