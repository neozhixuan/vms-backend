package com.vms.backend.repositories;

import com.vms.backend.entities.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, Integer> {
  Shift findById(int id);
}
