package com.vms.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.List;

@Table(name = "participant")
@Entity
@PrimaryKeyJoinColumn(name = "participant_id") // Specify the primary key column for the join
public class Participant extends Person {

  @OneToMany
  @JsonIgnore
  @JoinColumn(name = "participant_id")
  private List<ParticipantAvailability> availabilities;

  @OneToMany
  @JsonIgnore
  @JoinColumn(name = "participant_id")
  private List<Shift> shifts;

  public List<ParticipantAvailability> getAvailabilities() {
    return availabilities;
  }

  public void setAvailabilities(List<ParticipantAvailability> availabilities) {
    this.availabilities = availabilities;
  }

  public List<Shift> getShifts() {
    return shifts;
  }

  public void setShifts(List<Shift> shifts) {
    this.shifts = shifts;
  }
  //////////////////////////////////

}
