package com.vms.backend.pojos;

import java.time.LocalDateTime;

public class ShiftRequest {

  private LocalDateTime start_time;
  private LocalDateTime end_time;

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
}
