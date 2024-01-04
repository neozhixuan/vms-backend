package com.vms.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "participantAvailability")
@Entity
public class ParticipantAvailability {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "availability_id")
  private int id;

  private Boolean is_allocated;

  @ManyToOne
  @JoinColumn(name = "participant_id")
  private Participant participant;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "event_id")
  private Event event;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "shift_id")
  private Shift shift;

  ///////////////////////////////////////

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Boolean getIs_allocated() {
    return is_allocated;
  }

  public void setIs_allocated(Boolean is_allocated) {
    this.is_allocated = is_allocated;
  }

  public Participant getParticipant() {
    return participant;
  }

  public void setParticipant(Participant participant) {
    this.participant = participant;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public Shift getShift() {
    return shift;
  }

  public void setShift(Shift shift) {
    this.shift = shift;
  }
}
