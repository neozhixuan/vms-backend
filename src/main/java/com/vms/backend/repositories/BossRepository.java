package com.vms.backend.repositories;

import com.vms.backend.entities.Boss;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BossRepository extends JpaRepository<Boss, Integer> {
  Boss findById(int id);

  Optional<Boss> findByEmailAndPassw(String email, String passw);
}
