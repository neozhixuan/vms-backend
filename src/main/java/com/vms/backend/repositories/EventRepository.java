package com.vms.backend.repositories;

import com.vms.backend.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
  Event findById(int id);
}
