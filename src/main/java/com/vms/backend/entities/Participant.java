package com.vms.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Table(name = "participant")
@Entity
@PrimaryKeyJoinColumn(name = "participant_id") // Specify the primary key column for the join
public class Participant extends Person {

  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
