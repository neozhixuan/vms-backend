package com.vms.backend.repositories;

import com.vms.backend.entities.ParticipantAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantAvailabilityRepository
  extends JpaRepository<ParticipantAvailability, Integer> {}
