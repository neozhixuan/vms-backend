package com.vms.backend.repositories;

import com.vms.backend.entities.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Integer> {
  Event findById(int id);

  @Query("select e from Event e where e.boss.id= ?1%")
  List<Event> findBossEvents(int id);
}
