package com.vms.backend.pojos;

import java.util.List;

public class EventRequest {

  private int id;
  private String title;
  private String description;
  private int boss_id;
  private List<ShiftRequest> shifts;

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

  public int getBoss_id() {
    return boss_id;
  }

  public void setBoss_id(int boss_id) {
    this.boss_id = boss_id;
  }

  public List<ShiftRequest> getShifts() {
    return shifts;
  }

  public void setShifts(List<ShiftRequest> shifts) {
    this.shifts = shifts;
  }
}
