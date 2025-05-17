package com.vms.backend.repositories;

import java.util.Optional;

import com.vms.backend.entities.Boss;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BossRepository extends JpaRepository<Boss, Integer> {
  Optional<Boss> findById(int id);

  Optional<Boss> findByEmailAndPassw(String email, String passw);
}
