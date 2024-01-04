package com.vms.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Table(name = "event")
@Entity
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "event_id")
  private int id;

  private String title;
  private String description;

  @ManyToOne
  @JoinColumn(name = "boss_id")
  private Boss boss;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "event_id")
  private List<Shift> shifts;

  @OneToMany
  @JoinColumn(name = "event_id")
  private List<ParticipantAvailability> availabilities;

  @Override
  public String toString() {
    return String.format(
      "Event{id=%d, title='%s', description='%s', boss=%s, shifts=%s, availabilities=%s}",
      id,
      title,
      description,
      boss,
      shifts,
      availabilities
    );
  }

  /////////////////////////////////

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boss getBoss() {
    return boss;
  }

  public void setBoss(Boss boss) {
    this.boss = boss;
  }

  public List<Shift> getShifts() {
    return shifts;
  }

  public void setShifts(List<Shift> shifts) {
    this.shifts = shifts;
  }

  public List<ParticipantAvailability> getAvailabilities() {
    return availabilities;
  }

  public void setAvailabilities(List<ParticipantAvailability> availabilities) {
    this.availabilities = availabilities;
  }
}
