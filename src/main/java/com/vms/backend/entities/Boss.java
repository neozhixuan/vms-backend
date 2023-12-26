package com.vms.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Table(name = "boss")
@Entity
@PrimaryKeyJoinColumn(name = "boss_id") // Specify the primary key column for the join
public class Boss extends Person {

  private String usern;
  private String passw;

  public String getUsern() {
    return usern;
  }

  public void setUsern(String usern) {
    this.usern = usern;
  }

  public String getPassw() {
    return passw;
  }

  public void setPassw(String passw) {
    this.passw = passw;
  }
}
