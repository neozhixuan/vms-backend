package com.vms.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "shift")
@Entity
public class Shift {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "shift_id")
  private int id;

  private LocalDateTime start_time;
  private LocalDateTime end_time;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "event_id")
  private Event event;

  @OneToMany(mappedBy = "shift")
  @JsonIgnore
  // @JoinColumn(name = "shift_id")
  private List<ParticipantAvailability> availabilities;

  ////////////////////////////////

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDateTime getStart_time() {
    return start_time;
  }

  public void setStart_time(LocalDateTime start_time) {
    this.start_time = start_time;
  }

  public LocalDateTime getEnd_time() {
    return end_time;
  }

  public void setEnd_time(LocalDateTime end_time) {
    this.end_time = end_time;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public List<ParticipantAvailability> getAvailabilities() {
    return availabilities;
  }

  public void setAvailabilities(List<ParticipantAvailability> availabilities) {
    this.availabilities = availabilities;
  }
}
