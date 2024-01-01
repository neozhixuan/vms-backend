package com.vms.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.List;

@Table(name = "boss")
@Entity
@PrimaryKeyJoinColumn(name = "boss_id") // Specify the primary key column for the join
public class Boss extends Person {

  private String usern;
  private String company;
  private String birthday;

  @OneToMany
  @JsonIgnore
  @JoinColumn(name = "boss_id")
  private List<Event> events;

  ////////////////////////////////////////

  public String getUsern() {
    return usern;
  }

  public void setUsern(String usern) {
    this.usern = usern;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }
}
