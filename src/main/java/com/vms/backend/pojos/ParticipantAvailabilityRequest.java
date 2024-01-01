package com.vms.backend.pojos;

public class ParticipantAvailabilityRequest {

  private Boolean is_allocated;
  private int participant_id;
  private int event_id;
  private int shift_id;

  public Boolean getIs_allocated() {
    return is_allocated;
  }

  public void setIs_allocated(Boolean is_allocated) {
    this.is_allocated = is_allocated;
  }

  public int getParticipant_id() {
    return participant_id;
  }

  public void setParticipant_id(int participant_id) {
    this.participant_id = participant_id;
  }

  public int getEvent_id() {
    return event_id;
  }

  public void setEvent_id(int event_id) {
    this.event_id = event_id;
  }

  public int getShift_id() {
    return shift_id;
  }

  public void setShift_id(int shift_id) {
    this.shift_id = shift_id;
  }
}
