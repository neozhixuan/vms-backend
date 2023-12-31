package com.vms.backend.repositories;

import com.vms.backend.entities.Participant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository
  extends JpaRepository<Participant, Integer> {
  Participant findById(int id);
  Optional<Participant> findByEmailAndPassw(String email, String passw);
}
